package com.acacia.nms.server.pojo.floodlight;

public class Status {
	private String host="localhost";
    private int ofport = 6633;
    private String uptime="unknown";
    private int free = 0;
    private int total= 0;
    private String healthy ="unknown";
//    private modules: [],
    private String moduleText;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getOfport() {
		return ofport;
	}
	public void setOfport(int ofport) {
		this.ofport = ofport;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getHealthy() {
		return healthy;
	}
	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}
	public String getModuleText() {
		return moduleText;
	}
	public void setModuleText(String moduleText) {
		this.moduleText = moduleText;
	}
    
}
