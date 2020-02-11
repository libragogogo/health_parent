package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleManagementDao {

    public Page<Role> selectByCondition(String queryString);

    public List<Permission> findAll();

    public List<Integer> findPermissionIdsByRoleId(Integer id);


    public void deleteAssocication(Integer id);

    public void setRoleAndPermission(Map map);
}
