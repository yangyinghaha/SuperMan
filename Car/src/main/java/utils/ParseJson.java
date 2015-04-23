package utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParseJson {

	public static List<Map<String,Object>> parseJson(String jsonString,String key){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				map = new HashMap<String, Object>();
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Iterator<String> iterator = jsonObject2.keys();
				while(iterator.hasNext()){
					String jsonKey = iterator.next();
					Object jsonValue = jsonObject2.get(jsonKey);
					if(jsonValue == null){
						jsonValue ="";
					}
					map.put(jsonKey, jsonValue);
					
				}
				list.add(map);
			}
		
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
}
