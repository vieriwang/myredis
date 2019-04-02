package com.myredis.service;

public interface StockService {
    /**
     * 第一种redis统计方法
     * 先判断key非空，再初始化
     * 判断值是否超出范围，再+1.
     * redis中key为num
     */
    public void cutNumber();

    /**
     * 第二种redis统计方法
     * 先判断key非空，再初始化使用setnx（特性：如果存在则初始化失败，避免2次重复初始化）
     * 系统先将值+1，完成后再判断值是否超出范围，（避免重复增加2次）
     * redis中key为num
     */
    public void cutNumber2();
}
