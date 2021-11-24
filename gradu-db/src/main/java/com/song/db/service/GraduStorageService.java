package com.song.db.service;

import com.github.pagehelper.PageHelper;
import com.song.db.mapper.GraduStorageMapper;
import com.song.db.domain.GraduStorage;
import com.song.db.domain.GraduStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GraduStorageService {
    @Autowired
    private GraduStorageMapper storageMapper;

    public void deleteByKey(String key) {
        GraduStorageExample example = new GraduStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    public void add(GraduStorage storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    public GraduStorage findByKey(String key) {
        GraduStorageExample example = new GraduStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);
    }

    public int update(GraduStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public GraduStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<GraduStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        GraduStorageExample example = new GraduStorageExample();
        GraduStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }
}
