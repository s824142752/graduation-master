package com.song.db.service;

import com.song.db.mapper.StatMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StatService {
    @Resource
    private StatMapper statMapper;


    public List<Map> statUser() {
        return statMapper.statUser();
    }

    public List<Map> statDayInfo() {
        return statMapper.statDayInfo();
    }

    public List<Map> statDiffCollegeUser() {
        return statMapper.statDiffCollegeUser();
    }

    public List<Map> statDayInfoByEChart() {
        return statMapper.statDayInfoByEChart();
    }
}
