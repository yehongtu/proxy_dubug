package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

/**
 * Created by Enzo Cotter on 2019/4/12.
 */
public class RequestInfo {

	private String url;
	private HttpMethod method;
	private HttpEntity<?> requestEntity;

	public String getUrl() {
		return url;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public HttpEntity<?> getRequestEntity() {
		return requestEntity;
	}

	/*	public ParameterizedTypeReference<?> getResponseType() {
			return responseType;
		}

		public Map<String, ?> getUriVariables() {
			return uriVariables;
		}*/
	public RequestInfo() {

	}

	public static RequestInfo parseRequestInfo(String request) {
		return JSON.parseObject(request.toString(), RequestInfo.class);
	}

	public RequestInfo(String url, HttpMethod method, HttpEntity<?> requestEntity) {
		this.url = url;
		this.method = method;
		this.requestEntity = requestEntity;
	}

	void fun() {
		String JSON_OBJ_STR = "";
		Hello person = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Hello>() {
		});

	}

	public static void main(String[] args) {
		Object o = JSON.toJSON(new Hello());
		System.out.println(o.toString());
		Hello person = JSON.parseObject(o.toString(), Hello.class);
		System.out.println(person);
	}

}
