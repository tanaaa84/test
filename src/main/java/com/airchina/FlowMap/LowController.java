package com.airchina.FlowMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

@ServerEndpoint(value = "/lowmap")
public class LowController {

	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	private static final Set<LowController> connections = new CopyOnWriteArraySet<LowController>();

	private final String nickname;
	private Session session;
	int j = 0;

	
	
	String sta1 = "{\"in\":\"1\"}";
	

	static ArrayList<String> hm = new ArrayList<String>();
	static ArrayList<String> hn = new ArrayList<String>();

	public LowController() {
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
	}

	@OnOpen
	public void start(Session session) {
		this.session = session;
		connections.add(this);
		String message = String.format("* %s %s", nickname, "has joined.");
		broadcast(message);
	}

	@OnClose
	public void end() {
		connections.remove(this);
		String message = String
				.format("* %s %s", nickname, "has disconnected.");
		broadcast(message);
	}

	@OnMessage
	public void incoming(String message) {
		// Never trust the client
		// TODO: 过滤输入的内容
		System.out.println("@@@@@incoming@@@@@");

		hm.add("121.544108,38.962345");
		hm.add("24.966431,60.317769");
		hm.add("111.821465,40.8555");
		hm.add("117.308106,31.779949");
		hm.add("120.43406,30.23319");
		hm.add("132.918949,34.440044");
		hm.add("109.705267,27.441259");
		hm.add("113.936892,22.319589");
		hm.add("98.313518,8.110981");
		hm.add("119.824448,49.212327");
		hm.add("122.00798,46.195369");
		hm.add("139.784889,35.549198");
		hm.add("112.5719,26.89384");
		hm.add("126.242867,45.622922");
		hm.add("122.359543,29.933813");
		
		
		
		
		hn.add("79.874382,37.039901");
		hn.add("114.409904,23.118891");
		hn.add("121.419353,28.55437");
		hn.add("107.012844,33.065579");
		hn.add("109.158268,26.317113");
		hn.add("-77.44812,38.953001");
		hn.add("-95.347595,30.028678");
		hn.add("126.433411,37.458508");
//		hm.add("121.544108,38.962345");
//		hm.add("24.966431,60.317769");
//		hm.add("111.821465,40.8555");
//		hm.add("117.308106,31.779949");
//		hm.add("120.43406,30.23319");
//		hm.add("132.918949,34.440044");
//		hm.add("109.705267,27.441259");
//		hm.add("113.936892,22.319589");
//		hm.add("98.313518,8.110981");
//		hm.add("119.824448,49.212327");
//		hm.add("122.00798,46.195369");
//		hm.add("139.784889,35.549198");
//		hm.add("112.5719,26.89384");
//		hm.add("126.242867,45.622922");
//		hm.add("122.359543,29.933813");
//		hm.add("79.874382,37.039901");
//		hm.add("114.409904,23.118891");
//		hm.add("121.419353,28.55437");
//		hm.add("107.012844,33.065579");
//		hm.add("109.158268,26.317113");
//		hm.add("-77.44812,38.953001");
//		hm.add("-95.347595,30.028678");
//		hm.add("126.433411,37.458508");
//		hm.add("121.544108,38.962345");
//		hm.add("24.966431,60.317769");
//		hm.add("111.821465,40.8555");
//		hm.add("117.308106,31.779949");
//		hm.add("120.43406,30.23319");
//		hm.add("132.918949,34.440044");
//		hm.add("109.705267,27.441259");
//		hm.add("113.936892,22.319589");
//		hm.add("98.313518,8.110981");
//		hm.add("119.824448,49.212327");
//		hm.add("122.00798,46.195369");
//		hm.add("139.784889,35.549198");
//		hm.add("112.5719,26.89384");
//		hm.add("126.242867,45.622922");
//		hm.add("122.359543,29.933813");
//		hm.add("79.874382,37.039901");
//		hm.add("114.409904,23.118891");
//		hm.add("121.419353,28.55437");
//		hm.add("107.012844,33.065579");
//		hm.add("109.158268,26.317113");
//		hm.add("-77.44812,38.953001");
//		hm.add("-95.347595,30.028678");
//		hm.add("126.433411,37.458508");
//		hm.add("121.544108,38.962345");
//		hm.add("24.966431,60.317769");
//		hm.add("111.821465,40.8555");
//		hm.add("117.308106,31.779949");
//		hm.add("120.43406,30.23319");
//		hm.add("132.918949,34.440044");
//		hm.add("109.705267,27.441259");
//		hm.add("113.936892,22.319589");
//		hm.add("98.313518,8.110981");
//		hm.add("119.824448,49.212327");
//		hm.add("122.00798,46.195369");
//		hm.add("139.784889,35.549198");
//		hm.add("112.5719,26.89384");
//		hm.add("126.242867,45.622922");
//		hm.add("122.359543,29.933813");
//		hm.add("79.874382,37.039901");
//		hm.add("114.409904,23.118891");
//		hm.add("121.419353,28.55437");
//		hm.add("107.012844,33.065579");
//		hm.add("109.158268,26.317113");
//		hm.add("-77.44812,38.953001");
//		hm.add("-95.347595,30.028678");
//		hm.add("126.433411,37.458508");
//		hm.add("121.544108,38.962345");
//		hm.add("24.966431,60.317769");
//		hm.add("111.821465,40.8555");
//		hm.add("117.308106,31.779949");
//		hm.add("120.43406,30.23319");
//		hm.add("132.918949,34.440044");
//		hm.add("109.705267,27.441259");
//		hm.add("113.936892,22.319589");
//		hm.add("98.313518,8.110981");
//		hm.add("119.824448,49.212327");
//		hm.add("122.00798,46.195369");
//		hm.add("139.784889,35.549198");
//		hm.add("112.5719,26.89384");
//		hm.add("126.242867,45.622922");
//		hm.add("122.359543,29.933813");
//		hm.add("79.874382,37.039901");
//		hm.add("114.409904,23.118891");
//		hm.add("121.419353,28.55437");
//		hm.add("107.012844,33.065579");
//		hm.add("109.158268,26.317113");
//		hm.add("-77.44812,38.953001");
//		hm.add("-95.347595,30.028678");
//		hm.add("126.433411,37.458508");
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < hm.size(); i++) {
			if (i != hm.size() - 1) {
				sb.append("\""+i+"\":["+ hm.get(i) +"],");
			}else{
				sb.append("\""+i+"\":["+ hm.get(i) +"]");
			}
		}
		sb.append("}");
		
		
		
		
//		
//		Map<String, Map<String, Object>> res = new HashMap<String, Map<String, Object>>();
//		Map<String, Object> coordinates = new HashMap<String, Object>();
//		coordinates.put("coordinates", hm);
//		Map<String, Object> info = new HashMap<String, Object>();
//		info.put("info", coordinates);
//		res.put("res", info);
//		
//		JSONObject jsonObject = JSONObject.fromObject(res);
		
		
		

		
		
		

		
	
	
		
	

//		 while (true) {
//		 for (int i = 0; i < hm.size(); i++) {
//		 broadcast(hm.get(i));
//		 try {
//		 Thread.sleep(200);
//		 } catch (InterruptedException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
//		 }
//		 }
//		System.out.println("12121212");
		System.out.println(sb.toString());
		
//		 while (true) {
		for (int i = 0; i < 2; i++) {// 为什么第二次才能有值
			broadcast(sb.toString());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("Chat Error: " + t.toString());
	}

	private static void broadcast(String msg) {
		for (LowController client : connections) {
			try {
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				System.out
						.println("Chat Error: Failed to send message to client");
				connections.remove(client);
				try {
					client.session.close();
				} catch (IOException e1) {
					// Ignore
				}
				String message = String.format("* %s %s", client.nickname,
						"has been disconnected.");
				broadcast(message);
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		// TODO: 过滤输入的内容
		System.out.println("@@@@@incoming@@@@@");

		hm.add("121.544108,38.962345");
		hm.add("24.966431,60.317769");
		hm.add("111.821465,40.8555");
		hm.add("117.308106,31.779949");
		hm.add("120.43406,30.23319");
		hm.add("132.918949,34.440044");
		hm.add("109.705267,27.441259");
		hm.add("113.936892,22.319589");
		hm.add("98.313518,8.110981");
		hm.add("119.824448,49.212327");
		hm.add("122.00798,46.195369");
		hm.add("139.784889,35.549198");
		hm.add("112.5719,26.89384");
		hm.add("126.242867,45.622922");
		hm.add("122.359543,29.933813");
		hm.add("79.874382,37.039901");
		hm.add("114.409904,23.118891");
		hm.add("121.419353,28.55437");
		hm.add("107.012844,33.065579");
		hm.add("109.158268,26.317113");
		hm.add("-77.44812,38.953001");
		hm.add("-95.347595,30.028678");
		hm.add("126.433411,37.458508");
		hm.add("121.544108,38.962345");
		hm.add("24.966431,60.317769");
		hm.add("111.821465,40.8555");
		hm.add("117.308106,31.779949");
		hm.add("120.43406,30.23319");
		hm.add("132.918949,34.440044");
		hm.add("109.705267,27.441259");
		hm.add("113.936892,22.319589");
		hm.add("98.313518,8.110981");
		hm.add("119.824448,49.212327");
		hm.add("122.00798,46.195369");
		hm.add("139.784889,35.549198");
		hm.add("112.5719,26.89384");
		hm.add("126.242867,45.622922");
		hm.add("122.359543,29.933813");
		hm.add("79.874382,37.039901");
		hm.add("114.409904,23.118891");
		hm.add("121.419353,28.55437");
		hm.add("107.012844,33.065579");
		hm.add("109.158268,26.317113");
		hm.add("-77.44812,38.953001");
		hm.add("-95.347595,30.028678");
		hm.add("126.433411,37.458508");
		hm.add("121.544108,38.962345");
		hm.add("24.966431,60.317769");
		hm.add("111.821465,40.8555");
		hm.add("117.308106,31.779949");
		hm.add("120.43406,30.23319");
		hm.add("132.918949,34.440044");
		hm.add("109.705267,27.441259");
		hm.add("113.936892,22.319589");
		hm.add("98.313518,8.110981");
		hm.add("119.824448,49.212327");
		hm.add("122.00798,46.195369");
		hm.add("139.784889,35.549198");
		hm.add("112.5719,26.89384");
		hm.add("126.242867,45.622922");
		hm.add("122.359543,29.933813");
		hm.add("79.874382,37.039901");
		hm.add("114.409904,23.118891");
		hm.add("121.419353,28.55437");
		hm.add("107.012844,33.065579");
		hm.add("109.158268,26.317113");
		hm.add("-77.44812,38.953001");
		hm.add("-95.347595,30.028678");
		hm.add("126.433411,37.458508");
		hm.add("121.544108,38.962345");
		hm.add("24.966431,60.317769");
		hm.add("111.821465,40.8555");
		hm.add("117.308106,31.779949");
		hm.add("120.43406,30.23319");
		hm.add("132.918949,34.440044");
		hm.add("109.705267,27.441259");
		hm.add("113.936892,22.319589");
		hm.add("98.313518,8.110981");
		hm.add("119.824448,49.212327");
		hm.add("122.00798,46.195369");
		hm.add("139.784889,35.549198");
		hm.add("112.5719,26.89384");
		hm.add("126.242867,45.622922");
		hm.add("122.359543,29.933813");
		hm.add("79.874382,37.039901");
		hm.add("114.409904,23.118891");
		hm.add("121.419353,28.55437");
		hm.add("107.012844,33.065579");
		hm.add("109.158268,26.317113");
		hm.add("-77.44812,38.953001");
		hm.add("-95.347595,30.028678");
		hm.add("126.433411,37.458508");
		hm.add("121.544108,38.962345");
		hm.add("24.966431,60.317769");
		hm.add("111.821465,40.8555");
		hm.add("117.308106,31.779949");
		hm.add("120.43406,30.23319");
		hm.add("132.918949,34.440044");
		hm.add("109.705267,27.441259");
		hm.add("113.936892,22.319589");
		hm.add("98.313518,8.110981");
		hm.add("119.824448,49.212327");
		hm.add("122.00798,46.195369");
		hm.add("139.784889,35.549198");
		hm.add("112.5719,26.89384");
		hm.add("126.242867,45.622922");
		hm.add("122.359543,29.933813");
		hm.add("79.874382,37.039901");
		hm.add("114.409904,23.118891");
		hm.add("121.419353,28.55437");
		hm.add("107.012844,33.065579");
		hm.add("109.158268,26.317113");
		hm.add("-77.44812,38.953001");
		hm.add("-95.347595,30.028678");
		hm.add("126.433411,37.458508");
		
		
		
//		
		Map<String, Map<String, Object>> res = new HashMap<String, Map<String, Object>>();
		Map<String, Object> coordinates = new HashMap<String, Object>();
		coordinates.put("coordinates", hm);
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("info", coordinates);
		res.put("res", info);
		
		JSONObject jsonObject = JSONObject.fromObject(res);
		
		System.out.println(jsonObject.toString());

	}
	
	
	
} 
