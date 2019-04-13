package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Enzo Cotter on 2019/4/12.
 */
@RestController
public class ProxyController {
  @Autowired
  RestTemplate restTemplate;

  @PostMapping(value = "", consumes = "application/json")
  public String proxy(@RequestBody(required = false) JSONObject requestInfo, HttpServletRequest request) throws IOException {
	ArrayList<String> proxiedRequests = (ArrayList<String>) requestInfo.get("requestInfo");
	String s1 = proxiedRequests.get(0);
	Object requestInfo1 = requestInfo.get("requestInfo");
	Object method = JSONObject.parseObject(s1).get("method");
	Object requestEntity = JSONObject.parseObject(s1).get("requestEntity");
	JSONObject requestEntityJson = JSONObject.parseObject(requestEntity.toString());
	Object headers = requestEntityJson.get("headers");
	Object body = requestEntityJson.get("body");
/*		JSONObject.parseObject(s1).get("url");
		JSONObject.parse(s1);*/
	String[] cmd = {"cmd", "/C", "cm"};
	Process p = Runtime.getRuntime().exec(cmd);
	BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));
	br.lines().forEach(System.out::println);
	return System.currentTimeMillis() + "";
  }

  @PostMapping(value = "/curl", consumes = "application/json")
  public String proxy2(@RequestBody Application app, HttpServletRequest request) throws IOException {
	//todo 利用curl执行请求

	/** curl -H "Content-Type:application/json" -X POST --data '{"message": 123}' http://localhost:8080/curl
	 通过命令行CURL发送POST请求
	 * */
	/**
	 *Windows环境下，需要使用3个"""
	 curl -H "Content-Type:application/json" -X POST http://localhost:8080/curl --data "{"""ip""": """192.185.20.33""","""moinitor""":"""235""","""port""":258}"
	 **/
	return "curl" + System.currentTimeMillis();
  }

  @GetMapping("")
  public String home(Model model) {
	Date date = new Date();
	Calendar instance = Calendar.getInstance();
	return instance.toString();
  }

  public ResponseEntity<String> executeQuery(RequestInfo proxyRequest) {
	String url = proxyRequest.getUrl();
	HttpMethod method = proxyRequest.getMethod();
	HttpEntity<?> requestEntity = proxyRequest.getRequestEntity();
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	ResponseEntity<String> proxyResponse = restTemplate.exchange(url, method, requestEntity, String.class);
	return proxyResponse;
  }


}
