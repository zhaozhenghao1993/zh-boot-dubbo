package net.zhenghao.zh.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * json工具类(fastjson)
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月30日 下午2:12:10
 * JSONUtils.java
 */
public class JSONUtils {

	/**
	 * Bean对象转JSON
	 * @param object
	 * @param dateFormatString
	 * @return
	 */
	public static String beanToJson(Object object, String dateFormatString) {
		if (object != null) {
			if (StringUtils.isEmpty(dateFormatString)) {
				return JSONObject.toJSONString(object);
			}
			return JSON.toJSONStringWithDateFormat(object, dateFormatString);
		}else {
			return null;
		}
	}
	
	
	public static String beanToJson(Object object) {
		if (object != null) {
			return JSON.toJSONString(object);
		}else {
			return null;
		}
	}
	
	/**
	 * String 转JSON字符串
	 * @param key
	 * @param value
	 * @return
	 */
	public static String stringToJsonByFastjson(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		return beanToJson(map,null);
	}
	
	
	/**
	 * 将json字符串转换成对象
	 * @param json
	 * @param clazz new 个对象进去
	 * @return
	 */
	public static Object jsonToBean(String json, Object clazz) {
		if (StringUtils.isEmpty(json) || clazz == null) {
			return null;
		}
		return JSON.parseObject(json, clazz.getClass());
	}
	
	/**
	 * json字符串转map
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, Map.class);
	}

	/**
	 *Map转name1=value1&name2=value2
	 * @param params Map类型的参数
	 * @return
	 */
	public static String mapToParams(Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		if (params == null) {
			return sb.toString();
		}
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		return sb.substring(0, sb.length()-1);
	}

}
