package com.song.wx.controller;

import com.song.core.util.ResponseUtil;
import com.song.db.domain.GraduUser;
import com.song.db.domain.Information;
import com.song.db.service.GraduInformationService;
import com.song.db.service.GraduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宋永涛
 * @date 2021/5/6 21:56
 */
@RestController
@RequestMapping("/wx/information")
@Validated
public class WxInformationController {
    @Autowired
    private GraduInformationService graduInformationService;

    @Resource
    private GraduUserService graduUserService;
    /**
     * 添加任务
     */
    @PostMapping("/add")
    public Object addInformation(@RequestBody Information information) {

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
     * 根据义工信息的不用状态查询到相应的义工信息：（0：未审核；1：已审核）
     * 获取前台的status
     */
    @GetMapping("/listByStatus")
    public Object list(Integer status) {
        List<Information> list = graduInformationService.listInformationsByStatus(status);
        System.out.println(list);

        return ResponseUtil.okList(list);
    }


}
