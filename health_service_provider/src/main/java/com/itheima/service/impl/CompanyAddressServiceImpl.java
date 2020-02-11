package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CompanyAddressDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CompanyAddress;
import com.itheima.service.CompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CompanyAddressService.class)
@Transactional
public class CompanyAddressServiceImpl implements CompanyAddressService {

    @Autowired
    private CompanyAddressDao companyAddressDao;

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        System.out.println("queryString = " + queryString);
        PageHelper.startPage(currentPage,pageSize);
        Page<CompanyAddress> page = companyAddressDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }


    //根据ID删除检查项
    public void deleteById(Integer id) {
        /*//判断当前检查项是否已经关联到检查组
        long count = CompanyAddressDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项已经被关联到检查组，不允许删除
            new RuntimeException();
        }*/
        companyAddressDao.deleteById(id);
    }

    @Override
    public List<CompanyAddress> findAll() {
        return companyAddressDao.findAll();
    }

    @Override
    public Result add(CompanyAddress companyAddress) {
        String address = companyAddress.getAddress();
        if(address==null||address.length()==0){
            return new Result(false, MessageConstant.ADD_COMPANYADDRESS_FAIL);
        }
        List<CompanyAddress> list=companyAddressDao.findByAddress(address);
        if(list!=null&&list.size()>0){
            return new Result(false, MessageConstant.ADD_COMPANYADDRESS_FAIL);
        }else{
            companyAddressDao.add(companyAddress);
            return  new Result(true, MessageConstant.ADD_COMPANYADDRESS_SUCCESS);
        }
    }

}
