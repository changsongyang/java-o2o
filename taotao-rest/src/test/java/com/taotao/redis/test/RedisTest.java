package com.taotao.redis.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisTest {

	// 单机测试
	@Test
	public void testJedisPool() {
		// 创建一个连接池对象
		// 系统中应该是单例的。
		JedisPool jedisPool = new JedisPool("192.168.34.229", 6379);
		// 从连接池中获得一个连接
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get("redis06");
		System.out.println(result);
		// jedis必须关闭
		jedis.close();

		// 系统关闭时关闭连接池
		jedisPool.close();

	}

	// 集群测试
	@Test
	public void testJedisCluster() {
		// 创建一个JedisCluster对象
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.34.229", 7001));
		nodes.add(new HostAndPort("192.168.34.229", 7002));
		nodes.add(new HostAndPort("192.168.34.229", 7003));
		nodes.add(new HostAndPort("192.168.34.229", 7004));
		nodes.add(new HostAndPort("192.168.34.229", 7005));
		nodes.add(new HostAndPort("192.168.34.229", 7006));
		// 在nodes中指定每个节点的地址
		// jedisCluster在系统中是单例的。
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("name", "zhangsan");
		jedisCluster.set("value", "100");
		String name = jedisCluster.get("name");
		String value = jedisCluster.get("value");
		System.out.println(name);
		System.out.println(value);

		// 系统关闭时关闭jedisCluster
		jedisCluster.close();

	}

}
