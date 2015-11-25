package com.liz.common.util;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zuoliqiang
 * Date: 11-12-19
 * Time: A.M11:09
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * <pre>
     *     Map<String, Object> map = new HashMap<Object, String>();
     *     map.put("1", "1");
     *     List<String> list = new ArrayList<String>();
     *     list.add("e1");
     *     list.add("e2");
     *     map.put("2", list);
     *     String json = JsonUtil.parse(map);
     *     //json value is :
     * <pre>
     * @param obj Object instance
     * @return JSON String
     */
    public static String parse(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException("parse object to json error!", e);
		}
	}

    /**
     *
     * @param json's String
     * @param clazz of the json's Type
     * @return Object with json's format
     */
    public static Object format(String json, Class clazz) {
		try {
			return objectMapper.readValue(json,clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回由对象的属性为key,值为map的value的Map集合
	 *
	 * @param obj Object
	 * @return mapValue Map<String,String>
	 * @throws Exception
	 */
	public static Map<String, String> getFieldVlaue(Object obj) throws Exception {
		Map<String, String> mapValue = new HashMap<String, String>();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.toString().contains(" final ")) {
				continue;
			}
			String name = field.getName();
			String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			Method methodGet = cls.getDeclaredMethod(strGet);
			Object object = methodGet.invoke(obj);
			String value = object != null ? object.toString() : "";
			mapValue.put(name, value);
		}
		return mapValue;
	}
}
