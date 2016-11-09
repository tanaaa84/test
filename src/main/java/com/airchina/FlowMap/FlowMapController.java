package com.airchina.FlowMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
  
@ServerEndpoint(value = "/flowmap")  
public class FlowMapController {  
  
    private static final String GUEST_PREFIX = "Guest";  
    private static final AtomicInteger connectionIds = new AtomicInteger(0);  
    private static final Set<FlowMapController> connections =  new CopyOnWriteArraySet<FlowMapController>();  
  
    private final String nickname;  
    private Session session;  
    
    
    String str = "{\"info\": {\"coordinates\": {\"item\": ["
    		+ "{\"-longitude\": \"234\",\"-latitude\": \"100\"},"
    		+ "{\"-longitude\": \"123\",\"-latitude\": \"232\"}"
    		+ "]}}}" ;
    
    ArrayList<String> hm = new ArrayList<String> ();
 
    
    
  
    public FlowMapController() {  
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
        String message = String.format("* %s %s",  
                nickname, "has disconnected.");  
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
    	
    	

        
    	for (int i = 0; i < hm.size(); i++) {

    		broadcast(hm.get(i));  
            
			try {
				Thread.sleep(300);
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
        for (FlowMapController client : connections) {  
            try {  
                synchronized (client) {  
                    client.session.getBasicRemote().sendText(msg);  
                }  
            } catch (IOException e) {  
                System.out.println("Chat Error: Failed to send message to client");  
                connections.remove(client);  
                try {  
                    client.session.close();  
                } catch (IOException e1) {  
                    // Ignore  
                }  
                String message = String.format("* %s %s",  
                        client.nickname, "has been disconnected.");  
                broadcast(message);  
            }  
        }  
    }  
}  