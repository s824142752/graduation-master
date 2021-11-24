package com.song.db.service;

import com.github.pagehelper.PageHelper;
import com.song.db.mapper.GraduFeedbackMapper;
import com.song.db.domain.GraduFeedback;
import com.song.db.domain.GraduFeedbackExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宋永涛
 * @date 2021/2/5 20:12
 */

@Service
public class GraduFeedbackService {
    @Autowired
    private GraduFeedbackMapper feedbackMapper;

    public Integer add(GraduFeedback feedback) {
        feedback.setAddTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        return feedbackMapper.insertSelective(feedback);
    }

    public List<GraduFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        GraduFeedbackExample example = new GraduFeedbackExample();
        GraduFeedbackExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return feedbackMapper.selectByExample(example);
    }
}
