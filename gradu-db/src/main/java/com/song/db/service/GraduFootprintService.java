package com.song.db.service;

import com.github.pagehelper.PageHelper;
import com.song.db.mapper.GraduFootprintMapper;
import com.song.db.domain.GraduFootprint;
import com.song.db.domain.GraduFootprintExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GraduFootprintService {
    @Resource
    private GraduFootprintMapper footprintMapper;

    public List<GraduFootprint> queryByAddTime(Integer userId, Integer page, Integer size) {
        GraduFootprintExample example = new GraduFootprintExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        example.setOrderByClause(GraduFootprint.Column.addTime.desc());
        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }

    public GraduFootprint findById(Integer id) {
        return footprintMapper.selectByPrimaryKey(id);
    }

    public GraduFootprint findById(Integer userId, Integer id) {
        GraduFootprintExample example = new GraduFootprintExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return footprintMapper.selectOneByExample(example);
    }

    public void deleteById(Integer id) {
        footprintMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(GraduFootprint footprint) {
        footprint.setAddTime(LocalDateTime.now());
        footprint.setUpdateTime(LocalDateTime.now());
        footprintMapper.insertSelective(footprint);
    }

    public List<GraduFootprint> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order) {
        GraduFootprintExample example = new GraduFootprintExample();
        GraduFootprintExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.valueOf(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }
}
