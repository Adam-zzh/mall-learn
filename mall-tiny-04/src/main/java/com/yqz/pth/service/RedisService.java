package com.yqz.pth.service;

/*
  *@Description 操作redis接口
  *@author zzh
  *@Param
  *@return
  **/
public interface RedisService {
    /*
      *@Description 存储数据
      **/
    void set(String key, String value);

    /*
     *@Description 获取数据
     **/
    String get(String key);

    /*
     *@Description 设置超时时间
     **/
    boolean expire(String key, long expire);

    /*
     *@Description 删除key
     **/
    void delete(String key);

    /*
      *@Description 自增长
      * incBy 增长步长
      **/
    Long increment(String key, long incBy);

}
