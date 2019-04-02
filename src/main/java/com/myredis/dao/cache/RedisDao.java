package com.myredis.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
    public static final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    /**
     * 第一种redis统计方法
     * 先判断key非空，再初始化
     * 判断值是否超出范围，再+1.
     * redis中key为num
     */
    public void getNumber() {
        Jedis jedis = jedisPool.getResource();
        try {
            String x = jedis.get("num");
            if (x == null) {
                jedis.set("num", "0");
                x = "0";
            }
            Integer num = Integer.parseInt(x);
            if (num < 10) {
                logger.info("Plan1 Get it");
                jedis.incrBy("num", 1l);
            } else {
                logger.info("Plan1 Fail now");
            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 第二种redis统计方法
     * 先判断key非空，再初始化使用setnx（特性：如果存在则初始化失败，避免2次重复初始化）
     * 系统先将值+1，完成后再判断值是否超出范围，（避免重复增加2次）
     * redis中key为num
     */
    public void getNumberPlan2() {
        Long increment = 1l;
        Jedis jedis = jedisPool.getResource();
        try {
            String x = jedis.get("num2");
            if (x == null) {
                jedis.setnx("num2", "0");
            }
            Long newValue = jedis.incrBy("num2", increment);
            if (newValue < 11) {
                logger.info("Plan2 Get it");
            } else {
                logger.info("Plan2 Fail now");
            }
        } finally {
            jedis.close();
        }

    }
}
