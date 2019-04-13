package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

/**
 * Created by Enzo Cotter on 2019/4/11.
 */

public class Hello {


	public static void main(String[] args) throws URISyntaxException {
		new Hello().hello();
	}

	public void hello() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://127.0.0.1:8080?ange=3";
		HttpMethod method = HttpMethod.GET;
		RequestInfo requestProxied = originalRequest();
		String jsonRequest = JSON.toJSON(requestProxied).toString();
		JSONObject jsonObject = JSON.parseObject(jsonRequest);
		RequestInfo requestInfo = jsonObject.toJavaObject(RequestInfo.class);
		RequestInfo t = JSON.toJavaObject(jsonObject, RequestInfo.class);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("requestInfo", jsonRequest);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("age", "333");
		headers.add("Content-Type", "application/json;charset-UTF-8");

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);


		System.out.println(exchange.getBody());
	}


	/*public RequestInfo originalRequest() {
		String url = "http://www.baidu.com";
		HttpMethod method = HttpMethod.POST;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("age", "333");
		headers.add("Content-Type", "application/json;charset-UTF-8");
		MultiValueMap<String, Hello> body = new LinkedMultiValueMap<>();
		body.add("who", new Hello());
		HttpEntity<MultiValueMap<String, Hello>> requestEntity = new HttpEntity<>(body, headers);
		RequestInfo requestInfo = new RequestInfo(url, method, requestEntity);
		return requestInfo;
	}*/

	public RequestInfo originalRequest() {
		String url = "http://127.0.0.1/curl";
		HttpMethod method = HttpMethod.POST;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("age", "333");
		headers.add("Content-Type", "application/json;charset=UTF-8");
		MultiValueMap<String, Hello> body = new LinkedMultiValueMap<>();
		body.add("who", new Hello());
		HttpEntity<MultiValueMap<String, Hello>> requestEntity = new HttpEntity<>(body, headers);
		RequestInfo requestInfo = new RequestInfo(url, method, requestEntity);
		return requestInfo;
	}


	public void world() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://127.0.0.1:8080";
		HttpMethod method = HttpMethod.POST;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("age", "333");
		headers.add("Content-Type", "application/json");
		MultiValueMap<String, RequestInfo> body = new LinkedMultiValueMap<>();
		HttpEntity<MultiValueMap<String, RequestInfo>> requestEntity = new HttpEntity<>(body, headers);


		body.add("proxyRequest", new RequestInfo(url, method, requestEntity));

		ResponseEntity<String> exchange = restTemplate.exchange(url, method, requestEntity, String.class);
		System.out.println(exchange.getBody());
	}

	int helloWord = 333;


}
