package com.acacia.nms.server.pojo.floodlight;

public class Switch {
	private String datapathDescription;
	private String hardwareDescription;
	private String manufacturerDescription;
	private String serialNumber;
	private String softwareDescription;
	private String flowCount;
	private String packetCount;
	private String byteCount;
	public String getDatapathDescription() {
		return datapathDescription;
	}
	public void setDatapathDescription(String datapathDescription) {
		this.datapathDescription = datapathDescription;
	}
	public String getHardwareDescription() {
		return hardwareDescription;
	}
	public void setHardwareDescription(String hardwareDescription) {
		this.hardwareDescription = hardwareDescription;
	}
	public String getManufacturerDescription() {
		return manufacturerDescription;
	}
	public void setManufacturerDescription(String manufacturerDescription) {
		this.manufacturerDescription = manufacturerDescription;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSoftwareDescription() {
		return softwareDescription;
	}
	public void setSoftwareDescription(String softwareDescription) {
		this.softwareDescription = softwareDescription;
	}
	public String getFlowCount() {
		return flowCount;
	}
	public void setFlowCount(String flowCount) {
		this.flowCount = flowCount;
	}
	public String getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}
	public String getByteCount() {
		return byteCount;
	}
	public void setByteCount(String byteCount) {
		this.byteCount = byteCount;
	}
	
}
