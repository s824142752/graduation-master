//package com.song.wx.web;
//
//import com.github.pagehelper.PageInfo;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import com.song.core.util.JacksonUtil;
//import com.song.core.util.ResponseUtil;
//import com.song.db.domain.GraduFootprint;
//import com.song.db.service.GraduFootprintService;
//import com.song.wx.annotation.LoginUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 足迹服务
// */
//@RestController
//@RequestMapping("/wx/footprint")
//@Validated
//public class WxFootprintController {
//    private final Log logger = LogFactory.getLog(WxFootprintController.class);
//
//    @Autowired
//    private GraduFootprintService footprintService;
//
//    /**
//     * 删除用户足迹
//     *
//     * @param userId 用户ID
//     * @param body   请求内容， { id: xxx }
//     * @return 删除操作结果
//     */
//    @PostMapping("delete")
//    public Object delete(@LoginUser Integer userId, @RequestBody String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        if (body == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        Integer footprintId = JacksonUtil.parseInteger(body, "id");
//        if (footprintId == null) {
//            return ResponseUtil.badArgument();
//        }
//        GraduFootprint footprint = footprintService.findById(userId, footprintId);
//
//        if (footprint == null) {
//            return ResponseUtil.badArgumentValue();
//        }
//        if (!footprint.getUserId().equals(userId)) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        footprintService.deleteById(footprintId);
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 用户足迹列表
//     *
//     * @param page 分页页数
//     * @param limit 分页大小
//     * @return 用户足迹列表
//     */
//    @GetMapping("list")
//    public Object list(@LoginUser Integer userId,
//                       @RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer limit) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//
//        List<GraduFootprint> footprintList = footprintService.queryByAddTime(userId, page, limit);
//
//        List<Object> footprintVoList = new ArrayList<>(footprintList.size());
//        for (GraduFootprint footprint : footprintList) {
//            Map<String, Object> c = new HashMap<String, Object>();
//            c.put("id", footprint.getId());
//            c.put("goodsId", footprint.getGoodsId());
//            c.put("addTime", footprint.getAddTime());
//
//            GraduGoods goods = goodsService.findById(footprint.getGoodsId());
//            c.put("name", goods.getName());
//            c.put("brief", goods.getBrief());
//            c.put("picUrl", goods.getPicUrl());
//            c.put("retailPrice", goods.getRetailPrice());
//
//            footprintVoList.add(c);
//        }
//
//        return ResponseUtil.okList(footprintVoList, footprintList);
//    }
//
//}