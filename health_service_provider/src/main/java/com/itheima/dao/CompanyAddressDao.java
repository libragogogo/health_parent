package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CompanyAddress;

import java.util.List;

public interface CompanyAddressDao {

    public Page<CompanyAddress> findByCondition(String queryString);

    public void deleteById(Integer id);

    List<CompanyAddress> findAll();

    List<CompanyAddress> findByAddress(String address);

    void add(CompanyAddress companyAddress);

}
