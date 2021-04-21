package com.yqz.pth.service;

import com.github.pagehelper.PageInfo;
import com.yqz.pth.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
     /*
      *@Description  按照名称 产品数量查找
      *@author zzh
      *@Param
      *@return 
      **/
    public List<PmsBrand> getlist(String name, Integer minProductCount, Integer maxProductCount);

    /*
      *@Description 分页查询
      *@author zzh
      *@Param
      *@return
      **/
    public PageInfo<PmsBrand> getListByPaginition(Integer pageNo, Integer pageSize);
}

