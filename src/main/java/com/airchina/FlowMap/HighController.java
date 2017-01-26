package com.airchina.FlowMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.airchina.util.TimeHelper;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import net.sf.json.JSONObject;

@ServerEndpoint(value = "/highmap")
public class HighController {

	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	private static final Set<HighController> connections = new CopyOnWriteArraySet<HighController>();

	private final String nickname;
	private Session session;
	int j = 0;

	String sta1 = "{\"in\":\"1\"}";

	static ArrayList<String> hm = new ArrayList<String>();

	public HighController() {
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();

		Properties props = new Properties();
		// zookeeper 配置
		props.put("zookeeper.connect", "10.9.242.53:2181");

		// group 代表一个消费组
		props.put("group.id", "airchina-group");

		// zk连接超时
		props.put("zookeeper.session.timeout.ms", "4000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "smallest");
		// 序列化类
		props.put("serializer.class", "kafka.serializer.StringEncoder");

		ConsumerConfig config = new ConsumerConfig(props);

		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
		// consume();
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

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(AddCoordinateData.TOPIC, new Integer(1));

		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
		KafkaStream<String, String> stream = consumerMap.get(AddCoordinateData.TOPIC).get(0);
		ConsumerIterator<String, String> it = stream.iterator();

		long time1 = System.currentTimeMillis();

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		long i = 0;
		while (it.hasNext()) {
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		 while (true) {
//			 String ssString="{\"1\":[116.26998,39.897489],\"2\":[121.46915,31.243797],\"3\":[116.37200,40.017673],\"4\":[113.12692,41.023986],\"5\":[116.41177,39.941388],\"6\":[111.38248,35.627988],\"7\":[116.41177,39.941388],\"8\":[120.39974,36.280731],\"9\":[121.09558,31.146174],\"10\":[114.33571,30.55145]}";
//			 broadcast(ssString);
//			 try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//		 }
			
			final String msg = it.next().message();
//			System.out.println(msg);
			sb.append("\"" + i + "\":[" + msg + "],");
			long time2 = System.currentTimeMillis() - time1;
			if (time2 > 300) {
				String str = sb.substring(0, sb.length() - 1);
				str += ("}");
				System.out.println(str);
				
				for (int j = 0; j < 2; j++) {
					broadcast(str);
				}
				
				try {
					Thread.sleep(100);
					time1 = System.currentTimeMillis();
					sb.setLength(0);
					sb.append("{");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			i++;
		}

		// while (true) {
		// broadcast(sb.toString());
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}

	private final ConsumerConnector consumer;

//	void consume() {
//		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//		topicCountMap.put(AddCoordinateData.TOPIC, new Integer(1));
//
//		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
//		StringDecoder valueDecoder = new StringDecoder(
//				new VerifiableProperties());
//
//		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer
//				.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
//		KafkaStream<String, String> stream = consumerMap.get(
//				AddCoordinateData.TOPIC).get(0);
//		ConsumerIterator<String, String> it = stream.iterator();
//		while (it.hasNext()) {
//			final String msg = it.next().message();
//			System.out.println(msg);
//			// incoming(msg);
//
//			// Thread thread = new Thread() {
//			// public void run() {
//			// incoming(msg);
//			// }
//			// };
//			// thread.start();
//
//		}
//
//	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("Chat Error: " + t.toString());
	}

	private static void broadcast(String msg) {
		for (HighController client : connections) {
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

//	public static void main(String[] args) {
//		new HighController().consume();
//	}

	//
	//
	// public static void main(String[] args) {
	//
	// // TODO: 过滤输入的内容
	// System.out.println("@@@@@incoming@@@@@");
	//
	// hm.add("121.544108,38.962345");
	// hm.add("24.966431,60.317769");
	// hm.add("111.821465,40.8555");
	// hm.add("117.308106,31.779949");
	// hm.add("120.43406,30.23319");
	// hm.add("132.918949,34.440044");
	// hm.add("109.705267,27.441259");
	// hm.add("113.936892,22.319589");
	// hm.add("98.313518,8.110981");
	// hm.add("119.824448,49.212327");
	// hm.add("122.00798,46.195369");
	// hm.add("139.784889,35.549198");
	// hm.add("112.5719,26.89384");
	// hm.add("126.242867,45.622922");
	// hm.add("122.359543,29.933813");
	// hm.add("79.874382,37.039901");
	// hm.add("114.409904,23.118891");
	// hm.add("121.419353,28.55437");
	// hm.add("107.012844,33.065579");
	// hm.add("109.158268,26.317113");
	// hm.add("-77.44812,38.953001");
	// hm.add("-95.347595,30.028678");
	// hm.add("126.433411,37.458508");
	// hm.add("121.544108,38.962345");
	// hm.add("24.966431,60.317769");
	// hm.add("111.821465,40.8555");
	// hm.add("117.308106,31.779949");
	// hm.add("120.43406,30.23319");
	// hm.add("132.918949,34.440044");
	// hm.add("109.705267,27.441259");
	// hm.add("113.936892,22.319589");
	// hm.add("98.313518,8.110981");
	// hm.add("119.824448,49.212327");
	// hm.add("122.00798,46.195369");
	// hm.add("139.784889,35.549198");
	// hm.add("112.5719,26.89384");
	// hm.add("126.242867,45.622922");
	// hm.add("122.359543,29.933813");
	// hm.add("79.874382,37.039901");
	// hm.add("114.409904,23.118891");
	// hm.add("121.419353,28.55437");
	// hm.add("107.012844,33.065579");
	// hm.add("109.158268,26.317113");
	// hm.add("-77.44812,38.953001");
	// hm.add("-95.347595,30.028678");
	// hm.add("126.433411,37.458508");
	// hm.add("121.544108,38.962345");
	// hm.add("24.966431,60.317769");
	// hm.add("111.821465,40.8555");
	// hm.add("117.308106,31.779949");
	// hm.add("120.43406,30.23319");
	// hm.add("132.918949,34.440044");
	// hm.add("109.705267,27.441259");
	// hm.add("113.936892,22.319589");
	// hm.add("98.313518,8.110981");
	// hm.add("119.824448,49.212327");
	// hm.add("122.00798,46.195369");
	// hm.add("139.784889,35.549198");
	// hm.add("112.5719,26.89384");
	// hm.add("126.242867,45.622922");
	// hm.add("122.359543,29.933813");
	// hm.add("79.874382,37.039901");
	// hm.add("114.409904,23.118891");
	// hm.add("121.419353,28.55437");
	// hm.add("107.012844,33.065579");
	// hm.add("109.158268,26.317113");
	// hm.add("-77.44812,38.953001");
	// hm.add("-95.347595,30.028678");
	// hm.add("126.433411,37.458508");
	// hm.add("121.544108,38.962345");
	// hm.add("24.966431,60.317769");
	// hm.add("111.821465,40.8555");
	// hm.add("117.308106,31.779949");
	// hm.add("120.43406,30.23319");
	// hm.add("132.918949,34.440044");
	// hm.add("109.705267,27.441259");
	// hm.add("113.936892,22.319589");
	// hm.add("98.313518,8.110981");
	// hm.add("119.824448,49.212327");
	// hm.add("122.00798,46.195369");
	// hm.add("139.784889,35.549198");
	// hm.add("112.5719,26.89384");
	// hm.add("126.242867,45.622922");
	// hm.add("122.359543,29.933813");
	// hm.add("79.874382,37.039901");
	// hm.add("114.409904,23.118891");
	// hm.add("121.419353,28.55437");
	// hm.add("107.012844,33.065579");
	// hm.add("109.158268,26.317113");
	// hm.add("-77.44812,38.953001");
	// hm.add("-95.347595,30.028678");
	// hm.add("126.433411,37.458508");
	// hm.add("121.544108,38.962345");
	// hm.add("24.966431,60.317769");
	// hm.add("111.821465,40.8555");
	// hm.add("117.308106,31.779949");
	// hm.add("120.43406,30.23319");
	// hm.add("132.918949,34.440044");
	// hm.add("109.705267,27.441259");
	// hm.add("113.936892,22.319589");
	// hm.add("98.313518,8.110981");
	// hm.add("119.824448,49.212327");
	// hm.add("122.00798,46.195369");
	// hm.add("139.784889,35.549198");
	// hm.add("112.5719,26.89384");
	// hm.add("126.242867,45.622922");
	// hm.add("122.359543,29.933813");
	// hm.add("79.874382,37.039901");
	// hm.add("114.409904,23.118891");
	// hm.add("121.419353,28.55437");
	// hm.add("107.012844,33.065579");
	// hm.add("109.158268,26.317113");
	// hm.add("-77.44812,38.953001");
	// hm.add("-95.347595,30.028678");
	// hm.add("126.433411,37.458508");
	//
	//
	//
	// //
	// Map<String, Map<String, Object>> res = new HashMap<String, Map<String,
	// Object>>();
	// Map<String, Object> coordinates = new HashMap<String, Object>();
	// coordinates.put("coordinates", hm);
	// Map<String, Object> info = new HashMap<String, Object>();
	// info.put("info", coordinates);
	// res.put("res", info);
	//
	// JSONObject jsonObject = JSONObject.fromObject(res);
	//
	// System.out.println(jsonObject.toString());
	//
	// }

}