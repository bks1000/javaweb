package com.june.core.utils;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

/**
 * redis 操作
 * @author lenovo
 *
 */
//@Component("redisUtil") //暂时去掉，需要时，请先打开配置
public class RedisUtil {

	//@Resource(name="jedisdb")//暂时去掉
	private Jedis jedis; 
	
	public RedisUtil(){
		System.out.println("init!");
		
	}
	
	public void saveString(String key,String value) {
		jedis.del(key);
		jedis.set(key, value);
	}
	
	/**
	 * 保存Map
	 * @param key
	 * @param map
	 */
	public void saveMap(String key,Map<String, String> map) {
		jedis.del(key);
		jedis.hmset(key, map);
	}
	
	/**
	 * 保存任意类型
	 * @param key
	 * @param value
	 */
	public void saveObject(byte[] key, byte[] value) {
		jedis.set(key, value);
	}
	
	public String getString(String key) {
		return jedis.get(key);
	}
	
	/**
	 * 获取Map中的值
	 * @param key 
	 * @param fields map中的key
	 * @return
	 */
	public List<String> getMap(String key,String...fields) {
		return jedis.hmget(key, fields);
	}
	
	/**
	 * 获取任意类型
	 * @param key
	 * @return
	 */
	public byte[] getObject(byte[] key) {
		return jedis.get(key);
	}
}
