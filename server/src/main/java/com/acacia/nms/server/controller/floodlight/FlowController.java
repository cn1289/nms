package com.acacia.nms.server.controller.floodlight;

import java.util.HashMap;
import java.util.Map;

import net.floodlightcontroller.util.MACAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
@Controller
public class FlowController {
	private RestTemplate restTemplate;
	private String floodLightIp;
	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	@RequestMapping("getLongAddress")
	public @ResponseBody Long getLongAddress(@RequestParam("macAddress") String macAddress){
		return MACAddress.valueOf(macAddress).toLong();
	}
	@RequestMapping("addRules")
	public@ResponseBody String setRules(@RequestParam("src-mac")String srcMac,
			@RequestParam("nw-proto")String nwProto,
			@RequestParam("tp-src")String tpSrc,
			@RequestParam("action")String action){
		Map<String, String> map = new HashMap<String, String>();  
		map.put("src-mac", srcMac);
		map.put("nw-proto", nwProto);
		map.put("tp-src", tpSrc);
		map.put("action", action);
		return restTemplate.postForObject("http://"+this.floodLightIp+":8080/wm/firewall/rules/json", map,String.class);
	}
	@RequestMapping("deleteRules")
	public @ResponseBody String deleteRules(@RequestParam("ruleid")String ruleid){
		Map<String, String> map = new HashMap<String, String>();  
		map.put("ruleid", ruleid);
		System.out.println("ruleid="+ruleid);
		try{
			restTemplate.delete("http://"+this.floodLightIp+":8080/wm/firewall/rules/json", map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("wm/firewall/rules/json")
	public@ResponseBody String getRules(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/firewall/rules/json", String.class);
	}
	@RequestMapping("wm/core/health/json")
	public@ResponseBody String getHealth(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/core/health/json", String.class);
	}
	@RequestMapping("wm/core/memory/json")
	public@ResponseBody String getMemory(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/core/memory/json", String.class);
	}
	@RequestMapping("wm/core/module/loaded/json")
	public@ResponseBody String getModule(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/core/module/loaded/json", String.class);
	}
	@RequestMapping("wm/core/controller/switches/json")
	public@ResponseBody String getSwitchs(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/core/controller/switches/json", String.class);
	}
	@RequestMapping("wm/topology/links/json")
	public@ResponseBody String getLinks(){
		String json=restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/topology/links/json", String.class);
		System.out.println(json);
		return json;
	}
	@RequestMapping("wm/core/system/uptime/json")
	public@ResponseBody String getUptime(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/core/system/uptime/json", String.class);
	}
	@RequestMapping("wm/core/switch/{macAdress}/aggregate/json")
	public@ResponseBody String getAggregate(@PathVariable("macAdress")String macAdress){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/core/switch/"+macAdress+"/aggregate/json", String.class);
	}
	@RequestMapping("wm/core/switch/{macAdress}/desc/json")
	public@ResponseBody String getDesc(@PathVariable("macAdress")String macAdress){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/core/switch/"+macAdress+"/desc/json", String.class);
	}
	@RequestMapping("wm/device/")
	public@ResponseBody String getDevice(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/device/", String.class);
	}
	@RequestMapping("topology/")
	public@ResponseBody String topology(){
		return restTemplate.getForObject("http://"+this.floodLightIp+":8080/wm/device/", String.class);
	}
	@Value("#{server['acacia.floodlight.ip']}")
	public void setFloodLightIp(String floodLightIp) {
		this.floodLightIp = floodLightIp;
	}
}
