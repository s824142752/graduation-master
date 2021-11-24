package com.song.db.service;

import com.song.db.mapper.InformationMapper;
import com.song.db.domain.Information;
import com.song.db.domain.InformationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GraduInformationService {

    @Resource
    private InformationMapper informationMapper;

    public List<Information> listInformationsByStatus(Integer status){
        System.out.println("GraduInfomationService第23行"+status+"我是状态");
        List<Information> information = informationMapper.listInformationsByStatus(status);
        return information;
    }

    public List<Information> listInformationsByUsername(String username){
        System.out.println("GraduInfomationService第29行"+username+"我是学号");
        List<Information> information = informationMapper.listInformationsByUsername(username);
        int count = 0;
        for (int i = 0;i<information.size();i++){
            count++;

        }
        System.out.println(count);
        return information;
    }

//    //按照获取义工信息
//    public List<Information> getInfomationByUsername(String username) {
//        InformationExample example = new InformationExample();
//        example.or().andUsernameEqualTo(username);
//        return informationMapper.selectByExample(example);
//    }

    public int updateById(Information information) {
        return informationMapper.updateByPrimaryKeySelective(information);
    }

    public void addInformation(Information information) {
        informationMapper.insert(information);
    }

    public Information getInformation(Long id) {
        Information information = informationMapper.selectByPrimaryKey(id);
        return information;
    }

    public int count() {
        InformationExample example = new InformationExample();
        return (int) informationMapper.countByExample(example);
    }


}
