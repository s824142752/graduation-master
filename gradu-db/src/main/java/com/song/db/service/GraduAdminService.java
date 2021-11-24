package com.song.db.service;

import com.github.pagehelper.PageHelper;
import com.song.db.mapper.GraduAdminMapper;
import com.song.db.domain.GraduAdmin;
import com.song.db.domain.GraduAdminExample;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宋永涛
 * @date 2021/1/5 11:39
 */

@Service
public class GraduAdminService {
    private final GraduAdmin.Column[] result = new GraduAdmin.Column[]{GraduAdmin.Column.id, GraduAdmin.Column.username, GraduAdmin.Column.avatar, GraduAdmin.Column.roleIds};
    @Resource
    private GraduAdminMapper adminMapper;

    //按照姓名查找
    public List<GraduAdmin> findAdmin(String username) {
        GraduAdminExample example = new GraduAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }


    public List<GraduAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        GraduAdminExample example = new GraduAdminExample();
        GraduAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }

    /*
    * 更新
    * */
    public int updateById(GraduAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    /*
    * 删除
    * */
    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    /*
    * 添加
    * */
    public void add(GraduAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }
    //按照id查找
    public GraduAdmin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    public GraduAdmin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /*
    * 查看全部
    * */
    public List<GraduAdmin> all() {
        GraduAdminExample example = new GraduAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
