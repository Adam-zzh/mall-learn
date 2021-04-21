package com.yqz.zzh.elasticsearch.service;

import com.yqz.zzh.elasticsearch.nosql.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/*
  *@Description 商品搜索管理service
  *@author zzh
  *@Param
  *@return 
  **/
public interface EsProductService {

    //从数据库导入所有商品到ES
    int importAll();
    //根据id删除商品
    void  delete(Long id);
    //根据id创建商品
    EsProduct create(Long id);
    //批量删除商品
    void delete(List<Long> ids);

    //根据关键词搜索商品名称或副标题
    Page<EsProduct> search(String keyWord, Integer pageNum, Integer pageSize);

}
