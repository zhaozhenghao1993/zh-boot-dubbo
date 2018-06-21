package net.zhenghao.zh.common.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 工具类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2018年1月4日 下午4:06:02
 * RedisUtils.java
 */
public class RedisUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);
	
	/**
	 * Redis服务器IP
	 */
	private static String IP = PropertiesUtils.getInstance("redis").get("redis.ip");
	
	/**
	 * Redis服务器端口号
	 */
	private static int PORT = PropertiesUtils.getInstance("redis").getInt("redis.port");
	
	/**
	 * 访问密码
	 */
	private static String PASSWORD = PropertiesUtils.getInstance("redis").get("redis.password");
	
	/**
	 * 可用连接实例的最大数目，默认值为8；
	 * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	 */
	private static int MAX_ACTIVE = PropertiesUtils.getInstance("redis").getInt("redis.max_active");
	
	/**
	 * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	 */
	private static int MAX_IDLE = PropertiesUtils.getInstance("redis").getInt("redis.max_idle");
	
	/**
	 * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	 */
	private static int MAX_WAIT = PropertiesUtils.getInstance("redis").getInt("redis.max_wait");
	
	/**
	 * 超时时间
	 */
	private static int TIMEOUT = PropertiesUtils.getInstance("redis").getInt("redis.timeout");
	
	/**
	 * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	 */
	private static boolean TEST_ON_BORROW = false;
	
	private static JedisPool jedisPool = null;
	
	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, IP, PORT, TIMEOUT);
		} catch (Exception e) {
			LOGGER.error("First create JedisPool error : " + e);
		}
	}
	
	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (null == jedisPool) {
			initialPool();
		}
	}
	
	/**
	 * 同步获取Jedis实力
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		poolInit();
		Jedis jedis = null;
		try {
			if (null != jedisPool) {
				jedis = jedisPool.getResource();
				try {
					jedis.auth(PASSWORD);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			LOGGER.error("Get jedis error : " + e);
		}
		return jedis;
	}
	
	/**
	 * 存入String
	 * @param key
	 * @param value
	 */
	public synchronized static void set(String key, String value) {
		try {
			value = StringUtils.isBlank(value)? "" : value;
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("Set key error : " + e);
		}
	}
	
	/**
	 * 设置 byte[]
	 * @param key
	 * @param value
	 */
	public synchronized static void set(byte[] key, byte[] value) {
		try {
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("Set key error : " + e);
		}
	}
	
	/**
	 * 设置 String 过期时间
	 * @param key
	 * @param value
	 * @param seconds 以秒为单位
	 */
	public synchronized static void set(String key, String value, int seconds) {
		try {
			value = StringUtils.isBlank(value) ? "" : value;
			Jedis jedis = getJedis();
			jedis.setex(key, seconds, value);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("Set keyex error : " + e);
		}
	}
	
	/**
	 * 设置 byte[] 过期时间
	 * @param key
	 * @param value
	 * @param seconds 以秒为单位
	 */
	public synchronized static void set(byte[] key, byte[] value, int seconds) {
		try {
			Jedis jedis = getJedis();
			jedis.set(key, value);
			jedis.expire(key, seconds);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("Set key error : " + e);
		}
	}
	
	/**
	 * 获取String值
	 * @param key
	 * @return value
	 */
	public synchronized static String get(String key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	/**
	 * 获取byte[]值
	 * @param key
	 * @return value
	 */
	public synchronized static byte[] get(byte[] key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		byte[] value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	/**
	 * 删除值
	 * @param key
	 */
	public synchronized static void remove(String key) {
		try {
			Jedis jedis = getJedis();
			jedis.del(key);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("Remove keyex error : " + e);
		}
	}
	
	/**
	 * lpush
	 * @param key
	 * @param strings
	 * 1.lpush从左往右添加元素在key 对应 list的头部添加字符串元素
	 * 2.rpush从右到左添加元素在key 对应 list 的尾部添加字符串元素
	 */
	public synchronized static void lpush(String key, String... strings) {
		try {
			Jedis jedis = RedisUtils.getJedis();
			jedis.lpush(key, strings);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("lpush error : " + e);
		}
	}
	
	/**
	 * lrem
	 * @param key
	 * @param count
	 * @param value
	 * 从存于 key 的列表里移除前 count 次出现的值为 value 的元素。 
	 * 这个 count 参数通过下面几种方式影响这个操作：
	 * count > 0: 从头往尾移除值为 value 的元素。
	 * count < 0: 从尾往头移除值为 value 的元素。
	 * count = 0: 移除所有值为 value 的元素。
	 */
	public synchronized static void lrem(String key, long count, String value) {
		try {
			Jedis jedis = RedisUtils.getJedis();
			jedis.lrem(key, count, value);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("lpush error : " + e);
		}
	}
	
	/**
	 * sadd
	 * @param key
	 * @param value
	 * @param seconds
	 * 添加一个或多个指定的member元素到集合的 key中.
	 * 指定的一个或者多个元素member 如果已经在集合key中存在则忽略.
	 * 如果集合key 不存在，则新建集合key,并添加member元素到集合key中.
	 * 如果key 的类型不是集合则返回错误.
	 * 
	 * expire
	 * 设置key的过期时间，超过时间后，将会自动删除该key。
	 */
	public synchronized static void sadd(String key, String value, int seconds) {
		try {
			Jedis jedis = RedisUtils.getJedis();
			jedis.sadd(key, value);
			jedis.expire(key, seconds);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("sadd error : " + e);
		}
	}
	
	/**
	 * incr
	 * @param key
	 * @return value
	 * Redis Incr 命令将 key 中储存的数字值增一。
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
	 */
	public synchronized static Long incr(String key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		long value = jedis.incr(key);
		jedis.close();
		return value;
	}
	
	/**
	 * decr
	 * @param key
	 * @return value
	 * DECR key
	 * 将 key 中储存的数字值减一。
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
	 */
	public synchronized static Long decr(String key) {
		Jedis jedis = getJedis();
		if (null == jedis) {
			return null;
		}
		long value = jedis.decr(key);
		jedis.close();
		return value;
	}
}
