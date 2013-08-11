package com.acacia.nms.server.pojo.floodlight;

public class Flow {
	private Long receiveBytes=0l;
	private Long receivePackets=0l;
	private Long transmitBytes=0l;
	private Long transmitPackets=0l;
	public Long getReceiveBytes() {
		return receiveBytes;
	}
	public void setReceiveBytes(Long receiveBytes) {
		this.receiveBytes = receiveBytes;
	}
	public Long getReceivePackets() {
		return receivePackets;
	}
	public void setReceivePackets(Long receivePackets) {
		this.receivePackets = receivePackets;
	}
	public Long getTransmitBytes() {
		return transmitBytes;
	}
	public void setTransmitBytes(Long transmitBytes) {
		this.transmitBytes = transmitBytes;
	}
	public Long getTransmitPackets() {
		return transmitPackets;
	}
	public void setTransmitPackets(Long transmitPackets) {
		this.transmitPackets = transmitPackets;
	}
	
}
