package com.song.admin.controller;

import com.song.admin.annotation.RequiresPermissionsDesc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.song.core.util.ResponseUtil;
import com.song.core.validator.Order;
import com.song.core.validator.Sort;
import com.song.db.domain.GraduLog;
import com.song.db.service.GraduLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/log")
@Validated
public class AdminLogController {
    private final Log logger = LogFactory.getLog(AdminLogController.class);

    @Autowired
    private GraduLogService logService;

    @RequiresPermissions("admin:log:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "操作日志"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<GraduLog> logList = logService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(logList);
    }
}
