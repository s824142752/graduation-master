package com.song.db.service;

import com.song.db.mapper.GraduPermissionMapper;
import com.song.db.domain.GraduPermission;
import com.song.db.domain.GraduPermissionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GraduPermissionService {
    @Resource
    private GraduPermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        GraduPermissionExample example = new GraduPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<GraduPermission> permissionList = permissionMapper.selectByExample(example);

        for(GraduPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        GraduPermissionExample example = new GraduPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<GraduPermission> permissionList = permissionMapper.selectByExample(example);

        for(GraduPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        GraduPermissionExample example = new GraduPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        GraduPermissionExample example = new GraduPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(GraduPermission graduPermission) {
        graduPermission.setAddTime(LocalDateTime.now());
        graduPermission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(graduPermission);
    }
}
