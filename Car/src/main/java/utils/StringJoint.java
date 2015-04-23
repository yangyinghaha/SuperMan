package utils;

public class StringJoint {
	
	public static String joinString(String url){
		String baseUrl = "http://m.pcauto.com.cn/x/";
		String[] Array = url.split("/");
		String newstr = baseUrl + Array[4] + "/" + Array[5];
		return newstr;
	}

}
