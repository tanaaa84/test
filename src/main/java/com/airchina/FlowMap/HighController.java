package com.airchina.FlowMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import net.sf.json.JSONObject;

import com.airchina.util.RedisUtils;
import com.airchina.util.TimeHelper;

@ServerEndpoint(value = "/highmap")
@Singleton
public class HighController {

	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	private static final Set<HighController> connections = new CopyOnWriteArraySet<HighController>();


	private final ConsumerConnector consumer;
	private final String nickname;
	private Session session;

	RedisUtils ru = new RedisUtils();
	
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
		String message = String.format("* %s %s", nickname, "has disconnected.");
		broadcast(message);
	}

	@OnMessage
	public void incoming(String message) {
		System.out.println("@@@@@incoming@@@@@");

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(AddCoordinateData.TOPIC, new Integer(1));

		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
		KafkaStream<String, String> stream = consumerMap.get(AddCoordinateData.TOPIC).get(0);
		ConsumerIterator<String, String> it = stream.iterator();

		long time1 = System.currentTimeMillis();

		StringBuffer sb = new StringBuffer();
		
		
		sb.append("{coordinate:{");
		long i = 0;
		while (it.hasNext()) {
			/**
			 * try { Thread.sleep(100); } catch (InterruptedException e1) { //
			 * TODO Auto-generated catch block e1.printStackTrace(); } while
			 * (true) { String ssString=
			 * "{\"1\":[116.26998,39.897489],\"2\":[121.46915,31.243797],\"3\":[116.37200,40.017673],
			 * \"4\":[113.12692,41.023986],\"5\":[116.41177,39.941388],\"6\":[111.38248,35.627988],\"7\":
			 * [116.41177,39.941388],\"8\":[120.39974,36.280731],\"9\":[121.09558,31.146174],\"10\":[114.33571,30.55145]}"
			 * ; broadcast(ssString); try { Thread.sleep(5000); } catch
			 * (InterruptedException e) { e.printStackTrace(); } }
			 */
			// 坐标
			// { "coordinate": "12.33232,43.2323" }

			// 购票数，金额，PEKSHA
			// { "generalticket": { "price": "100","code": "peksha","time":"20170203123821"}}

			// 奖励客票数，里程数
			// { "amrticket": { "miles": "100","code": "peksha"}}

			// 里程购票数，里程数
			// { "milesticket": { "miles": "100","code": "peksha"}}

			// 改期数，金额
			// { "reshop": { "price": "100","code": "peksha"}}

			// 升舱数，金额
			// { "upgrade": { "price": "100","code": "peksha"}}

			// 付费选座数，金额
			// { "perseat": { "price": "100","code": "peksha"}}

			// 值机数。
			// { "checkin": { "price": "100","code": "peksha"}}

			final String msg = it.next().message();
			JSONObject jsonObject = JSONObject.fromObject(msg);

			if (jsonObject.get("coordinate") != null) {
				sb.append("\"" + i + "\":[" + jsonObject.get("coordinate")+ "],");
				long time2 = System.currentTimeMillis() - time1;
				if (time2 > 300) {
					String str = sb.substring(0, sb.length() - 1);
					str += ("}}");
					System.out.println(str);

					for (int j = 0; j < 2; j++) {// TODO 为什么会这样？
						broadcast(str);
					}

					try {
						Thread.sleep(100);
						time1 = System.currentTimeMillis();
						sb.setLength(0);
						sb.append("{coordinate:{");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i++;
			}else if (jsonObject.get("generalticket") != null) {
				JSONObject ticket = (JSONObject) jsonObject.get("generalticket");
				String time = ticket.getString("time");
				String rdsTicketKey =  time.substring(0, 8) + "totalprice";			//普通票销售在redis中的key
				long price = Long.parseLong(ticket.getString("price"));				//当前实时传递的订单
				long rdsPrice = Long.parseLong(ru.get(rdsTicketKey));				//redis中记录的当日合计价格
				long totalPrice = rdsPrice + price; // 日销售票价汇总			
				String jsonString = "{ \"generalticket\": { \"totalprice\": \"" + totalPrice + "\",\"time\": \"" + TimeHelper.dateTime2Str(Long.parseLong(time)) + "\"}}";
				System.out.println(jsonString);
				broadcast(jsonString);
				ru.set(rdsTicketKey, totalPrice + ""); //保存到redis
			}
		}
	}



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

	public static void main(String[] args) {
		// new HighController().consume();
		String str = "{ \"coordinate\": \"13.2323,23.4344\" }";
		JSONObject jsonObject = JSONObject.fromObject(str);
		System.out.println(jsonObject.get("wew"));
	}

}