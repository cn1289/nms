package com.acacia.nms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.util.UriTemplate;

public class MyTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UriTemplate template=new UriTemplate("http://baidu.com");
		Map<String,String> map=new HashMap<String,String>();
		map.put("a", "b");
		System.out.println(template.expand(map));
	}

}
