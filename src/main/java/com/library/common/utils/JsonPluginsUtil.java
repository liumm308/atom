package com.library.common.utils;

/**
 * Created with IntelliJ IDEA.
 * User: linyiming
 * Date: 13-8-12
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.*;

/**
 * 
 * Json与javaBean之间的转换工具类
 * 
 * 
 * 
 * @author ailk
 * 
 * @version 20111
 * 
 *          支持 * }
 */

public class JsonPluginsUtil {
	
	
	public static JSONObject strToJsonObj(String jsonString) {
		return   JSONObject.parseObject(jsonString);
	}

	
	//从YAPI挪过来的。
	public static String mapToString (Map map) {
	return JSON.toJSONString(map);
	}
	
	public static JSONArray strToJsonArray(String jsonString) {
		return   JSONArray.parseArray(jsonString);
	}
	
	/**
	 * 
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * 
	 * 
	 * @param jsonString
	 * 
	 * @param beanCalss
	 * 
	 * @return
	 */


	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		T bean = (T) JSONObject.toJavaObject(jsonObject, beanCalss);
		return bean;

	}

	/**
	 * 从一个json串中返回一个复杂的bean对象
	 * 
	 * @param obj
	 * @param beanCalss
	 * @return
	 */

	public static <T> T jsonObjToBean(JSONObject obj, Class<T> beanCalss) {
		T bean = (T) JSONObject.toJavaObject(obj, beanCalss);
		return bean;
	}

	/**
	 * 
	 * 将java对象转换成json字符串
	 * @param bean
	 * 
	 * @return
	 */
	public static String beanToJson(Object bean) {
		return JSONObject.toJSONString(bean, false);

	}

	public static JSONObject beanToJsonObj(Object bean) {
		return (JSONObject) JSONObject.toJSON(bean);
	}

	@SuppressWarnings("unchecked")
	public static String beanListToJson(List beans) {
		JSONArray array = beanListToJsonArray(beans);
		return array.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public static JSONArray beanListToJsonArray(List beans) {

		JSONArray rest = new JSONArray();
		for (int i = 0; i < beans.size(); i++) {
			rest.add(beanToJsonObj(beans.get(i)));
		}
		return rest;
	}

	/**
	 * 
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * @param jsonString
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public static Map jsonToMap(String jsonString) {

		String key;
		Map valueMap = new HashMap();
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		Set<String> sets = jsonObject.keySet();
		if (sets == null)
			return valueMap;
		Iterator keyIter = sets.iterator();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			valueMap.put(key, jsonObject.get(key));
		}
		return valueMap;
	}

	public static String mapToJson(Object map) {
		String s_json = "";
		s_json = JSONObject.toJSONString(map);
		return s_json;
	}

	/**
	 * 
	 * 从json数组中得到相应java数组
	 * @param jsonString
	 * 
	 * @return
	 */
	public static Object[] jsonToObjectArray(String jsonString) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * 
	 * 从json对象集合表达式中得到一个java对象列表
	 * @param jsonString
	 * 
	 * @param beanClass
	 * 
	 * @return
	 */
	public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		JSONObject jsonObject;
		T bean;
		int size = jsonArray.size();
		List<T> list = new ArrayList<T>(size);

		for (int i = 0; i < size; i++) {
			jsonObject = jsonArray.getJSONObject(i);
			bean = (T) jsonObjToBean(jsonObject, beanClass);
			list.add(bean);
		}
		return list;

	}
	
	public static <T> List<T> jsonArrayToBeanList(JSONArray jsonArray, Class<T> beanClass) {

		JSONObject jsonObject;
		T bean;
		int size = jsonArray.size();
		List<T> list = new ArrayList<T>(size);
		for (int i = 0; i < size; i++) {
			jsonObject = jsonArray.getJSONObject(i);
			bean = (T) jsonObjToBean(jsonObject, beanClass);
			list.add(bean);
		}
		return list;

	}

	/**
	 * 
	 * 从json数组中解析出java字符串数组
	 * 
	 * 
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public static String[] jsonToStringArray(String jsonString) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		int size = jsonArray.size();

		for (int i = 0; i < size; i++) {
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
	}

	/**
	 * 
	 * 从json数组中解析出javaLong型对象数组
	 * @param jsonString
	 * 
	 * @return
	 */
	public static Long[] jsonToLongArray(String jsonString) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);

		int size = jsonArray.size();

		Long[] longArray = new Long[size];

		for (int i = 0; i < size; i++) {

			longArray[i] = jsonArray.getLong(i);

		}

		return longArray;

	}

	/**
	 * 
	 * 从json数组中解析出java Integer型对象数组
	 * @param jsonString
	 * 
	 * @return
	 */

	public static Integer[] jsonToIntegerArray(String jsonString) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);

		int size = jsonArray.size();

		Integer[] integerArray = new Integer[size];

		for (int i = 0; i < size; i++) {

			integerArray[i] = jsonArray.getInteger(i);

		}

		return integerArray;

	}

	/**
	 * 
	 * 从json数组中解析出java Double型对象数组
	 * @param jsonString
	 * 
	 * @return
	 */

	public static Double[] jsonToDoubleArray(String jsonString) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);

		int size = jsonArray.size();

		Double[] doubleArray = new Double[size];

		for (int i = 0; i < size; i++) {

			doubleArray[i] = jsonArray.getDouble(i);

		}

		return doubleArray;

	}

	public static String toPrintString(Object obj) {
		
		return JSONArray.toJSONString(obj, true);
	}

	/**
	 * 判断json类型是是不是数组类型
	 */
	public static boolean isJsonArrayType(String jsonStr){
		boolean flag = false ;
		Gson gson = new Gson();  
		JsonParser ps = new JsonParser();
		JsonElement jsonEle = ps.parse(jsonStr);
		if(jsonEle.isJsonArray()){
			flag = true ;
		}
		return flag ;
	}
}