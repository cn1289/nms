package com.acacia.nms.server.pojo.floodlight;

public class Host {
    private String lastSeen ="never";
    private String ip;
    private String swport;
	public String getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSwport() {
		return swport;
	}
	public void setSwport(String swport) {
		this.swport = swport;
	} 
}
