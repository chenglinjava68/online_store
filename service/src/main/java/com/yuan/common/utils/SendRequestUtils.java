package com.yuan.common.utils;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;

public class SendRequestUtils {
	
	private static Logger log = Logger.getLogger("SendRequestUtils");
	/**
	 * 构造http或者https请求
	 * 
	 * @param url  请求的url地址
	 * @param body  http报文主体
	 * @param headers  构造http请求的请求头
	 * @param httpMethod  请求方式
	 * 
	 * @return responseBody 返回报文的body
	 * 
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public static String buildHttpRequest(String url,String body, Map<String,String> headers,
									HttpMethod httpMethod) throws URISyntaxException, IOException{
		
		URI uri = new URI(url);
		
		SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
		ClientHttpRequest chr = schr.createRequest(uri, httpMethod);
		
		//遍历headers
		if(headers!=null){
			for(Map.Entry<String,String> entry : headers.entrySet()){
				chr.getHeaders().set(entry.getKey(), entry.getValue());
			}
		}
		
		chr.getBody().write(body.getBytes("utf-8"));
		
		ClientHttpResponse res = chr.execute();
		
		int status = res.getRawStatusCode();
		
		if(status==200){
			InputStream is = res.getBody(); //获得返回数据,注意这里是个流
			String responseBody = StreamUtils.copyToString(is, Charset.forName("UTF-8"));
			
			return responseBody;
		}
		
		return null;
		
		
	}
	
	
	
}
