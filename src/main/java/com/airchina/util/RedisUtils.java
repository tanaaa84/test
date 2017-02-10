package com.airchina.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//    ./redis-cli -h 10.9.242.53 -a 123456 -p 6379

public class RedisUtils {
	private Jedis jedis;  
    private JedisPool jedisPool;  
  
    public RedisUtils() {  
        initialPool();  
        jedis = jedisPool.getResource();  
    }  
  
  
    private void initialPool() {  
        JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxActive(100);  
        config.setMaxIdle(20);  
        config.setMaxWait(1000l);  
        config.setTestOnBorrow(false);  
        jedisPool = new JedisPool(config, "10.9.242.53", 6379, 5000, "123456");
    }  
  
  
    public String get(String key) {  
        String getStr = jedis.get(key);  
        return getStr;  
    }  
  
    public String set(String key, String value) {  
        String setStr = jedis.set(key, value);  
        return setStr;  
    }  
    
    
    public static void main(String[] args) {
    	RedisUtils ru = new RedisUtils();
    //	ru.set("20170203price","111");
    	
    	System.out.println(ru.get("20170210totalprice"));
    	
	}
    
    
}
