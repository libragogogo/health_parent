package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CompanyAddress;
import com.itheima.service.CompanyAddressService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companyAddress")
public class CompanyAddressController {

    @Reference
    private CompanyAddressService companyAddressService;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return companyAddressService.pageQuery(queryPageBean);
    }


    //删除检查项
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            companyAddressService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.DELETE_COMPANYADDRESS_FAIL);
        }
        return  new Result(true, MessageConstant.DELETE_COMPANYADDRESS_SUCCESS);
    }

    @RequestMapping("/findAddress")
    public Result findAll(){
        try{
            List<CompanyAddress> list = companyAddressService.findAll();
            return  new Result(true, MessageConstant.QUERY_COMPANYADDRESS_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_COMPANYADDRESS_FAIL);
        }
    }


    //新增检查项
    @RequestMapping("/add")
    public Result add(@RequestBody CompanyAddress companyAddress){
            return companyAddressService.add(companyAddress);
    }
}
