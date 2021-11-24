package com.song.admin.controller;

import com.song.db.service.GraduInformationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.song.core.util.ResponseUtil;
import com.song.db.service.GraduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/dashboard")
@Validated
public class AdminDashbordController {
    private final Log logger = LogFactory.getLog(AdminDashbordController.class);

    @Autowired
    private GraduUserService userService;

    @Autowired
    private GraduInformationService graduInformationService;

    @GetMapping("")
    public Object info() {
        int userTotal = userService.count();
        int processedTotal = graduInformationService.count();
        Map<String, Integer> data = new HashMap<>();
        data.put("userTotal", userTotal);
        data.put("processedTotal", processedTotal);

        return ResponseUtil.ok(data);
    }


}
