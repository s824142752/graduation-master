package com.song.admin.controller;

import com.song.admin.annotation.RequiresPermissionsDesc;
import com.song.core.util.bcrypt.BCryptPasswordEncoder;
import com.song.db.domain.Information;
import com.song.db.service.GraduInformationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.song.core.util.ResponseUtil;
import com.song.core.validator.Order;
import com.song.core.validator.Sort;
import com.song.db.domain.GraduUser;
import com.song.db.service.GraduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {

    private final Log logger = LogFactory.getLog(AdminUserController.class);

    @Autowired
    private GraduUserService userService;

    @Resource
    private GraduInformationService graduInformationService;

    @Resource
    private AdminAuthController adminAuthController;

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "学生管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username, String mobile, String nickname,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<GraduUser> userList = userService.querySelective(username, mobile, nickname, page, limit, sort, order);
        System.out.println("userlist"+userList);
//        if (userList.size() != 0) {
//
//
//            Object info = adminAuthController.info();
//
////            System.out.println("获取data" + info);
//
////            List<GraduUser> list = userList;
////            System.out.println("list"+list);
////            for (int i = 0; i < userList.size(); i++) {
////
////            }
//        }
//        System.out.println(userList);
        return ResponseUtil.okList(userList);
    }

    @RequiresPermissions("admin:user:detail")
    @RequiresPermissionsDesc(menu = {"用户管理", "学生管理"}, button = "详情")
    @GetMapping("/detail")
    public Object userDetail(@NotNull Integer id) {
        GraduUser user = userService.findById(id);
        return ResponseUtil.ok(user);
    }

    @RequiresPermissions("admin:user:update")
    @RequiresPermissionsDesc(menu = {"用户管理", "学生管理"}, button = "编辑")
    @PostMapping("/update")
    public Object userUpdate(@RequestBody GraduUser user) {
        return ResponseUtil.ok(userService.updateById(user));
    }


    @RequiresPermissions("admin:user:add")
    @RequiresPermissionsDesc(menu = {"用户管理", "学生管理"}, button = "添加")
    @PostMapping("/add")
    public Object userAdd(@RequestBody GraduUser user) {
        System.out.println(user);

        String username = user.getUsername();
        List<GraduUser> graduUserList = userService.findUserByName(username);

        //加密
        String userPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userPassword);
        user.setPassword(encodedPassword);
        userService.add(user);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:user:addByExcel")
    @RequiresPermissionsDesc(menu = {"用户管理", "学生管理"}, button = "批量添加")
    @PostMapping("/addByExcel")
    public Object userAddByExcel(@RequestBody GraduUser user) {
//        System.out.println(user);
//        System.out.println(user.getPassword());
        //加密
        String userPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userPassword);
        user.setPassword(encodedPassword);
        userService.add(user);
        return ResponseUtil.ok();
    }


    @RequiresPermissions("admin:user:delete")
    @RequiresPermissionsDesc(menu = {"用户管理", "学生管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody GraduUser user) {
        Integer anotherUser = user.getId();
        userService.deleteById(anotherUser);
        return ResponseUtil.ok();
    }

    /*
     * 根据学号查学生义工信息详情
     * */
    @RequiresPermissions("admin:user:getInfoByUsername")
    @RequiresPermissionsDesc(menu = {"用户管理", "学生管理"}, button = "学生义工信息详情")
    @GetMapping("/getInfoByUsername")
    public Object getInfomationByUsername(String username) {

        List<Information> list = graduInformationService.listInformationsByUsername(username);

        for (int i = 0; i < list.size(); i++) {
            SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
            Information information = list.get(i);
            LocalDateTime createTime = list.get(i).getCreateTime();
//            System.out.println(createTime);
            LocalDate localDate = createTime.toLocalDate();
//            System.out.println(localDate);
            information.setDate(localDate);
//            System.out.println(list.get(i).getDate());
        }
//        System.out.println(list);
        return ResponseUtil.ok(list);
    }

}
