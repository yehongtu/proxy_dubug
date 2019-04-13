package com.example.demo;

/**
 * Created by Enzo Cotter on 2019/4/13.
 */
public class Application {
  String ip = "1.2.2.5";
  String monitor = "234";
  int port = 33;

  public String getIp() {
	return ip;
  }

  public void setIp(String ip) {
	this.ip = ip;
  }

  public String getMonitor() {
	return monitor;
  }

  public void setMonitor(String monitor) {
	this.monitor = monitor;
  }

  public int getPort() {
	return port;
  }

  public void setPort(int port) {
	this.port = port;
  }
}
