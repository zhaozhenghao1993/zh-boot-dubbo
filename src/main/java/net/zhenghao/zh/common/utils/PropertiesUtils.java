package net.zhenghao.zh.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * properties文件读取工具
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2018年1月4日 下午3:22:36
 * PropertiesUtils.java
 */
public class PropertiesUtils {

	/**
	 * 当打开多个资源文件时,缓存源文件
	 */
	private static HashMap<String, PropertiesUtils> configMap = new HashMap<String, PropertiesUtils>(16);
	
	/**
	 * 打开文件时间,判断超时使用
	 */
	private Date loadTime = null;
	
	/**
	 * 资源文件
	 * 这个类主要用来解决国际化和本地化问题
	 * 当程序需要一个特定于语言环境的资源时（如 String），
	 * 程序可以从适合当前用户语言环境的资源包（大多数情况下也就是.properties文件）中加载它。
	 * 这样可以编写很大程度上独立于用户语言环境的程序代码，它将资源包中大部分（即便不是全部）特定于语言环境的信息隔离开来。
	 */
	private ResourceBundle resourceBundle = null;
	
	/**
	 * 默认资源文件名称
	 */
	private static final String NAME = "config";
	
	/**
	 * 缓存时间
	 */
	private static final Integer TIME_OUT = 60 * 1000;

	/**
	 * 私有构造方法,创建单例
	 * @param name
	 */
	private PropertiesUtils(String name) {
		this.loadTime = new Date();
		this.resourceBundle = ResourceBundle.getBundle(name);
		/**
		 * ResourceBundle bundle = ResourceBundle.getBundle("res", new Locale("zh", "CN"));  
		 * 其中new Locale("zh", "CN");这个对象就告诉了程序你的本地化信息，就拿这个来说吧：程序首先会去classpath下寻找res_zh_CN.properties
		 * 若不存在，那么会去找res_zh.properties，若还是不存在，则会去寻找res.properties，要还是找不到的话，那么就该抛异常了：MissingResourceException
		 */
	}
	
	public static synchronized PropertiesUtils getInstance() {
		return getInstance(NAME);
	}
	
	public static synchronized PropertiesUtils getInstance(String name) {
		PropertiesUtils conf = configMap.get("name");
		if (null == conf) {
			conf = new PropertiesUtils(name);
			configMap.put(name, conf);
		}
		//判断是否打开的资源文件是否超时1分钟
		if ((System.currentTimeMillis() - conf.getLoadTime().getTime()) > TIME_OUT) {
			conf = new PropertiesUtils(name);
			configMap.put(name, conf);
		}
		return conf;
	}
	
	/**
	 * 根据key读取value
	 * @param key
	 * @return
	 */
	public String get(String key) {
		try {
			String value = resourceBundle.getString(key);
			return value;
		} catch (MissingResourceException e) {
			// TODO: handle exception
			return "";
		}
	}
	
	/**
     * 根据key读取value(整型)
     * @param key
     * @return
     */
    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.parseInt(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }
    
    /**
     * 根据key读取value(布尔值)
     * @param key
     * @return
     */
    public boolean getBool(String key) {
        String flag = "true";
        try {
            String value = resourceBundle.getString(key);
            if (flag.equals(value)) {
                return true;
            }
            return false;
        } catch (MissingResourceException e) {
            return false;
        }
    }
	
	public Date getLoadTime() {
		return loadTime;
	}
}
