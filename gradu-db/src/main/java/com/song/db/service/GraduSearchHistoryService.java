package com.song.db.service;

import com.github.pagehelper.PageHelper;
import com.song.db.mapper.GraduSearchHistoryMapper;
import com.song.db.domain.GraduSearchHistory;
import com.song.db.domain.GraduSearchHistoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GraduSearchHistoryService {
    @Resource
    private GraduSearchHistoryMapper searchHistoryMapper;

    public void save(GraduSearchHistory searchHistory) {
        searchHistory.setAddTime(LocalDateTime.now());
        searchHistory.setUpdateTime(LocalDateTime.now());
        searchHistoryMapper.insertSelective(searchHistory);
    }

    public List<GraduSearchHistory> queryByUid(int uid) {
        GraduSearchHistoryExample example = new GraduSearchHistoryExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        example.setDistinct(true);
        return searchHistoryMapper.selectByExampleSelective(example, GraduSearchHistory.Column.keyword);
    }

    public void deleteByUid(int uid) {
        GraduSearchHistoryExample example = new GraduSearchHistoryExample();
        example.or().andUserIdEqualTo(uid);
        searchHistoryMapper.logicalDeleteByExample(example);
    }

    public List<GraduSearchHistory> querySelective(String userId, String keyword, Integer page, Integer size, String sort, String order) {
        GraduSearchHistoryExample example = new GraduSearchHistoryExample();
        GraduSearchHistoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return searchHistoryMapper.selectByExample(example);
    }
}
