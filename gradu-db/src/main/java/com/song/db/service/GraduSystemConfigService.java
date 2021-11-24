package com.song.db.service;

import com.song.db.mapper.GraduSystemMapper;
import com.song.db.domain.GraduSystem;
import com.song.db.domain.GraduSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraduSystemConfigService {
    @Resource
    private GraduSystemMapper systemMapper;

    public Map<String, String> queryAll() {
        GraduSystemExample example = new GraduSystemExample();
        example.or().andDeletedEqualTo(false);

        List<GraduSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> systemConfigs = new HashMap<>();
        for (GraduSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    public Map<String, String> listMail() {
        GraduSystemExample example = new GraduSystemExample();
        example.or().andKeyNameLike("gradu_mall_%").andDeletedEqualTo(false);
        List<GraduSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(GraduSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listWx() {
        GraduSystemExample example = new GraduSystemExample();
        example.or().andKeyNameLike("gradu_wx_%").andDeletedEqualTo(false);
        List<GraduSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(GraduSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }



    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            GraduSystemExample example = new GraduSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            GraduSystem system = new GraduSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }

    public void addConfig(String key, String value) {
        GraduSystem system = new GraduSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemMapper.insertSelective(system);
    }
}
