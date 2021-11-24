package com.song.admin.controller;

import com.song.admin.annotation.RequiresPermissionsDesc;
import com.song.db.domain.GraduAdmin;
import com.song.db.domain.GraduNotice;
import com.song.db.domain.GraduNoticeAdmin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import com.song.core.util.JacksonUtil;
import com.song.core.util.ResponseUtil;
import com.song.core.validator.Order;
import com.song.core.validator.Sort;
import com.song.db.service.GraduAdminService;
import com.song.db.service.GraduNoticeAdminService;
import com.song.db.service.GraduNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.song.admin.util.AdminResponseCode.NOTICE_UPDATE_NOT_ALLOWED;

@RestController
@RequestMapping("/admin/notice")
@Validated
public class AdminNoticeController {
    private final Log logger = LogFactory.getLog(AdminNoticeController.class);

    @Autowired
    private GraduNoticeService noticeService;
    @Autowired
    private GraduAdminService adminService;
    @Autowired
    private GraduNoticeAdminService noticeAdminService;

    @RequiresPermissions("admin:notice:list")
    @RequiresPermissionsDesc(menu = {"通知管理", "通知管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String title, String content,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<GraduNotice> noticeList = noticeService.querySelective(title, content, page, limit, sort, order);
        return ResponseUtil.okList(noticeList);
    }

    private Object validate(GraduNotice notice) {
        String title = notice.getTitle();
        if (StringUtils.isEmpty(title)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    private Integer getAdminId(){
        Subject currentUser = SecurityUtils.getSubject();
        GraduAdmin admin = (GraduAdmin) currentUser.getPrincipal();
        return admin.getId();
    }

    @RequiresPermissions("admin:notice:create")
    @RequiresPermissionsDesc(menu = {"通知管理", "通知管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody GraduNotice notice) {
        Object error = validate(notice);
        if (error != null) {
            return error;
        }
        // 1. 添加通知记录
        notice.setAdminId(getAdminId());
        noticeService.add(notice);
        // 2. 添加管理员通知记录
        List<GraduAdmin> adminList = adminService.all();
        GraduNoticeAdmin noticeAdmin = new GraduNoticeAdmin();
        noticeAdmin.setNoticeId(notice.getId());
        noticeAdmin.setNoticeTitle(notice.getTitle());
        for(GraduAdmin admin : adminList){
            noticeAdmin.setAdminId(admin.getId());
            noticeAdminService.add(noticeAdmin);
        }
        return ResponseUtil.ok(notice);
    }

    @RequiresPermissions("admin:notice:read")
    @RequiresPermissionsDesc(menu = {"通知管理", "通知管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        GraduNotice notice = noticeService.findById(id);
        List<GraduNoticeAdmin> noticeAdminList = noticeAdminService.queryByNoticeId(id);
        Map<String, Object> data = new HashMap<>(2);
        data.put("notice", notice);
        data.put("noticeAdminList", noticeAdminList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:notice:update")
    @RequiresPermissionsDesc(menu = {"通知管理", "通知管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody GraduNotice notice) {
        Object error = validate(notice);
        if (error != null) {
            return error;
        }
        GraduNotice originalNotice = noticeService.findById(notice.getId());
        if (originalNotice == null) {
            return ResponseUtil.badArgument();
        }
        // 如果通知已经有人阅读过，则不支持编辑
        if(noticeAdminService.countReadByNoticeId(notice.getId()) > 0){
            return ResponseUtil.fail(NOTICE_UPDATE_NOT_ALLOWED, "通知已被阅读，不能重新编辑");
        }
        // 1. 更新通知记录
        notice.setAdminId(getAdminId());
        noticeService.updateById(notice);
        // 2. 更新管理员通知记录
        if(!originalNotice.getTitle().equals(notice.getTitle())){
            GraduNoticeAdmin noticeAdmin = new GraduNoticeAdmin();
            noticeAdmin.setNoticeTitle(notice.getTitle());
            noticeAdminService.updateByNoticeId(noticeAdmin, notice.getId());
        }
        return ResponseUtil.ok(notice);
    }

    @RequiresPermissions("admin:notice:delete")
    @RequiresPermissionsDesc(menu = {"通知管理", "通知管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody GraduNotice notice) {
        // 1. 删除通知管理员记录
        noticeAdminService.deleteByNoticeId(notice.getId());
        // 2. 删除通知记录
        noticeService.deleteById(notice.getId());
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:notice:batch-delete")
    @RequiresPermissionsDesc(menu = {"通知管理", "通知管理"}, button = "批量删除")
    @PostMapping("/batch-delete")
    public Object batchDelete(@RequestBody String body) {
        List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
        // 1. 删除通知管理员记录
        noticeAdminService.deleteByNoticeIds(ids);
        // 2. 删除通知记录
        noticeService.deleteByIds(ids);
        return ResponseUtil.ok();
    }
}
