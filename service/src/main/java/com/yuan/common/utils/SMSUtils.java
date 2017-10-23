package com.yuan.common.utils;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.util.Base64Utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SMSUtils {
	
	private static Logger log = Logger.getLogger(SMSUtils.class);
	private static String username;
	private static String password;
	private static String sign;
	public static Integer cancelTime;
	
	static {
		InputStream in = null;
		try {
			Properties pro = new Properties();
			in = SMSUtils.class.getClassLoader().getResourceAsStream("MSM.properties");
			pro.load(in);
			username = (String) pro.get("username");
			sign = (String) pro.get("sign");
			password = (String) pro.get("password");
			cancelTime = (Integer) pro.get("cancelTime");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != in){
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }
	
	public static String getVerCodeByPhone(String userPhone,String msg){
		
		try{
			String auth = "Basic " + Base64Utils.encodeToString((username + ":" + password).getBytes("utf-8"));
									
			String array = "mobile=" + userPhone + "&message=" + msg + sign;
			byte[] byteArray = array.getBytes("utf-8");
			
			Map<String,String> headers = new HashMap<String,String>();	
			headers.put("Authorization", auth);
			headers.put("ContentLength", String.valueOf(byteArray.length));
			headers.put("ContentType", "application/x-www-form-urlencoded");
	
			String res = SendRequestUtils.buildHttpRequest("http://sms-api.luosimao.com/v1/send.json", array, headers, HttpMethod.POST);
			
			return res;
		}catch(Exception e){
			log.error("发送获取验证码Http请求失败");
			e.printStackTrace();
		}
		return "";
	}
	
	public static String sendApoMsg(String userPhone,String msg){
		
		try{
			String auth = "Basic " + Base64Utils.encodeToString((username + ":" + password).getBytes("utf-8"));
									
			String array = "mobile=" + userPhone + "&message=" + msg + sign;
			byte[] byteArray = array.getBytes("utf-8");
			
			Map<String,String> headers = new HashMap<String,String>();	
			headers.put("Authorization", auth);
			headers.put("ContentLength", String.valueOf(byteArray.length));
			headers.put("ContentType", "application/x-www-form-urlencoded");
	
			String res = SendRequestUtils.buildHttpRequest("http://sms-api.luosimao.com/v1/send.json", array, headers, HttpMethod.POST);
			
			return res;
		}catch(Exception e){
			log.error("sendApoMsg失败");
			e.printStackTrace();
		}
		return "";
	}
}
