package com.song.admin.controller;

import com.song.admin.annotation.RequiresPermissionsDesc;
import com.song.admin.vo.StatVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.song.core.util.ResponseUtil;
import com.song.db.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/stat")
@Validated
public class AdminStatController {
    private final Log logger = LogFactory.getLog(AdminStatController.class);

    @Autowired
    private StatService statService;

    /*
    *
    * */
    @RequiresPermissions("admin:stat:user")
    @RequiresPermissionsDesc(menu = {"统计管理", "学生统计"}, button = "查询")
    @GetMapping("/user")
    public Object statUser() {
        List<Map> rows = statService.statUser();
        String[] columns = new String[]{"day", "users"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);
        return ResponseUtil.ok(statVo);
    }

    /*
     * 义工数量统计 - Daily Volunteer Information
     * */
    @RequiresPermissions("admin:stat:day")
    @RequiresPermissionsDesc(menu = {"统计管理", "义工信息统计"}, button = "查询")
    @GetMapping("/day")
    public Object statDayInfo() {
        List<Map> rows = statService.statDayInfo();
        String[] columns = new String[]{"date", "num"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);
        return ResponseUtil.ok(statVo);
    }

    /*
    * 学生统计 - Students from different colleges
    * */
    @RequiresPermissions("admin:stat:collegeUser")
    @RequiresPermissionsDesc(menu = {"统计管理", "学生统计"}, button = "学生统计")
    @GetMapping("/collegeUser")
    public Object statDiffCollegeUser() {
        List<Map> rows = statService.statDiffCollegeUser();
        String[] columns = new String[]{"name", "value"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);
        return ResponseUtil.ok(statVo);
    }

    /*
     * 学生统计 - Students from different colleges by Echars
     * */
    @RequiresPermissions("admin:stat:collegeUserEcharts")
    @RequiresPermissionsDesc(menu = {"统计管理", "学生统计"}, button = "学生统计-饼状图")
    @GetMapping("/collegeUserEcharts")
    public Object statDiffCollegeUserByEcharts() {
        List<Map> rows = statService.statDiffCollegeUser();
        String[] columns = new String[]{"name", "value"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);
        return ResponseUtil.ok(statVo);
    }



    /*
     * 义工数量统计 - Daily Volunteer Information by Echars
     * */
    @RequiresPermissions("admin:stat:day")
    @RequiresPermissionsDesc(menu = {"统计管理", "义工信息统计"}, button = "义工信息统计-饼状图")
    @GetMapping("/dayEcharts")
    public Object statDayInfoByEChart() {
        List<Map> rows = statService.statDayInfoByEChart();
        String[] columns = new String[]{"name", "value"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);
        return ResponseUtil.ok(statVo);
    }


}
