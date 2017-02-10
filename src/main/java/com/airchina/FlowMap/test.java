package com.airchina.FlowMap;

import java.util.Iterator;

import net.sf.json.JSONObject;

public class test {
public static void main(String[] args) {
	
	
	
	final String msg = "{ \"ticket\": { \"price\": \"100\",\"code\": \"peksha\"}}";
	JSONObject jsonObject = JSONObject.fromObject(msg);

	JSONObject ticket = (JSONObject) jsonObject.get("ticket");
	
	
	System.out.println(ticket.getString("price"));
	
//     Iterator iterator = ticket.keys();
//     while(iterator.hasNext()){
//        String key = (String) iterator.next();
//        String value = ticket.getString(key);
//        
//        System.out.println(key);
//        System.out.println(value);
//     }

}
}
