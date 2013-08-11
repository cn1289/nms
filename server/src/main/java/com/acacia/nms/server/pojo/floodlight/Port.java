package com.acacia.nms.server.pojo.floodlight;

public class Port {
	private String name;
    private long receiveBytes=0;
    private long receivePackets=0;
    private long transmitBytes=0;
    private long transmitPackets=0;
    private int dropped=0;
    private int errors=0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getReceiveBytes() {
		return receiveBytes;
	}
	public void setReceiveBytes(long receiveBytes) {
		this.receiveBytes = receiveBytes;
	}
	public long getReceivePackets() {
		return receivePackets;
	}
	public void setReceivePackets(long receivePackets) {
		this.receivePackets = receivePackets;
	}
	public long getTransmitBytes() {
		return transmitBytes;
	}
	public void setTransmitBytes(long transmitBytes) {
		this.transmitBytes = transmitBytes;
	}
	public long getTransmitPackets() {
		return transmitPackets;
	}
	public void setTransmitPackets(long transmitPackets) {
		this.transmitPackets = transmitPackets;
	}
	public int getDropped() {
		return dropped;
	}
	public void setDropped(int dropped) {
		this.dropped = dropped;
	}
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
    
}
