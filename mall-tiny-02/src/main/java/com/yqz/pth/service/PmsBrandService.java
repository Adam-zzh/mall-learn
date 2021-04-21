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
     List<PmsBrand> getlist(String name, Integer minProductCount, Integer maxProductCount);

    /*
      *@Description 分页查询
      *@author zzh
      *@Param
      *@return
      **/
    PageInfo<PmsBrand> getListByPaginition(Integer pageNo, Integer pageSize);

    List<PmsBrand> getAllBranks();

    int addBrand(PmsBrand brand);

    int updateById(Long id,PmsBrand brand);

    PageInfo<PmsBrand> queryBrandsByPaginition(PmsBrand brand, Integer pageNo, Integer pageSize);

    int deleteBrandById(Long id);

    PmsBrand getBrandById(Long id);
}

