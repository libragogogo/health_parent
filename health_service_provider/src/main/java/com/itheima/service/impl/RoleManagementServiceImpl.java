package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleManagementDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.RoleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleManagementService.class)
@Transactional
public class RoleManagementServiceImpl implements RoleManagementService {

    //注入DAO对象
    @Autowired
    private RoleManagementDao roleManagementDao;

    //检查项分页查询
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //select * from t_checkitem limit 0,10
        Page<Role> page = roleManagementDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<Role> rows = page.getResult();
        return new PageResult(total,rows);
    }

    public List<Permission> findAll() {
        return roleManagementDao.findAll();
    }


    public List<Integer> findPermissionIdsByRoleId(Integer id) {
        return roleManagementDao.findPermissionIdsByRoleId(id);
    }


    //编辑检查组信息，同时需要关联检查项
    public void add(Role role,Integer[] permissionIds) {
        //清理当前检查组关联的检查项，操作中间关系表t_checkgroup_checkitem表
        roleManagementDao.deleteAssocication(role.getId());
        //重新建立当前检查组和检查项的关联关系
        Integer roleId = role.getId();
        this.setRoleAndPermission(roleId,permissionIds);
    }


    //建立检查组和检查项多对多关系
    public void setRoleAndPermission(Integer roleId,Integer[] permissionIds){
        if(permissionIds != null && permissionIds.length > 0){

            for (Integer permissionId : permissionIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("roleId",roleId);
                map.put("permissionId",permissionId);
                roleManagementDao.setRoleAndPermission(map);
            }
        }
    }
}
