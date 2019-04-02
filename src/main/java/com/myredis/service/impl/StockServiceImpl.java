package com.myredis.service.impl;

import com.myredis.dao.cache.RedisDao;
import com.myredis.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockServiceImpl implements StockService {
    @Resource
    private RedisDao redisDao;

    @Override
    public void cutNumber2() {
        redisDao.getNumberPlan2();
    }

    @Override
    public void cutNumber() {
        redisDao.getNumber();
    }
}
