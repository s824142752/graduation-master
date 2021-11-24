package com.song.db.mapper;

import java.util.List;
import java.util.Map;

public interface StatMapper {
    List<Map> statUser();

    List<Map> statDayInfo();

    List<Map> statDiffCollegeUser();

    List<Map> statDayInfoByEChart();

}