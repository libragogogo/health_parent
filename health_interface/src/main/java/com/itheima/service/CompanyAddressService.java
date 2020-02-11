package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CompanyAddress;

import java.util.List;

public interface CompanyAddressService {

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void deleteById(Integer id);

    List<CompanyAddress> findAll();

    Result add(CompanyAddress companyAddress);


}
