package com.song.admin.controller;

import com.song.admin.annotation.RequiresPermissionsDesc;
import com.song.core.util.ResponseUtil;
import com.song.db.domain.GraduUser;
import com.song.db.domain.Information;
import com.song.db.service.GraduInformationService;
import com.song.db.service.GraduUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/information")
public class AdminInfomationController {

    @Resource
    private GraduInformationService graduInformationService;

    @Resource
    private GraduUserService graduUserService;

    /**
     * 根据义工信息的不用状态查询到相应的义工信息：（0：未审核；1：已审核）
     * 获取前台的status
     */
    @RequiresPermissions("admin:information:list")
    @RequiresPermissionsDesc(menu = {"信息管理", "已处理信息"}, button = "查询")
    @GetMapping("/listByStatus")
    public Object list(Integer status) {
        List<Information> list = graduInformationService.listInformationsByStatus(status);
        System.out.println(list);
        return ResponseUtil.okList(list);
    }

    /**
     * 添加任务
     */
    @RequiresPermissions("admin:information:list")
    @RequiresPermissionsDesc(menu = {"信息管理", "上传信息"}, button = "添加")
    @PostMapping("/add")
    public Object addInformation(@RequestBody Information information) {
        System.out.println(information);

        //从前台传过来的information中获取username
        String usernameInfo = information.getUsername();
        //调用graduUserService中的函数，
        List<GraduUser> graduUserList = graduUserService.findUserByName(usernameInfo);
        if (graduUserList.size() == 0){
            System.out.println("义工信息添加失败");
            return ResponseUtil.fail(-1,"不存在此学生");
        } else {
            //计算时间
            LocalDateTime timeCreate = information.getCreateTime();//获取起始时间
            LocalDateTime timeEnd = information.getEndTime();//获取结束时间
            Duration d = Duration.between(timeCreate, timeEnd);//时间差
            Long durationMinutes = d.toMinutes();//相差的分钟数
            information.setDuration(durationMinutes);
            //调用插入数据的方法，添加的数据库
            graduInformationService.addInformation(information);
            return ResponseUtil.ok(information);
        }
    }

    /**
     * 管理员处理义工信息:进行审核处理
     */
    @RequiresPermissions("admin:information:list")
    @RequiresPermissionsDesc(menu = {"信息管理", "已处理信息"}, button = "更新")
    @PostMapping("/update")
    public Object updateInformation(@RequestBody Information information) {
        //计算时间
        LocalDateTime timeCreate = information.getCreateTime();//获取起始时间
        LocalDateTime timeEnd = information.getEndTime();//获取起始时间
        Duration d = Duration.between(timeCreate, timeEnd);//时间差
        Long durationMinutes = d.toMinutes();//相差的分钟数
        //Long=》String类型的转换，将Long累型的时间差转换成String类型
//        String duration = durationMinutes.toString();
//        System.out.println("时间差"+duration);
        //将时间差添加到information中去
        information.setDuration(durationMinutes);
//        System.out.println(information);

        return ResponseUtil.ok(graduInformationService.updateById(information));
    }

}














