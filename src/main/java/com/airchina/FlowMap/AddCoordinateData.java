package com.airchina.FlowMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;



public class AddCoordinateData {
	@SuppressWarnings("deprecation")
	private final Producer<String, String> producer;
	public final static String TOPIC = "TEST-DATA";

	// public final static String TOPIC = "test";

	private AddCoordinateData() {
		Properties props = new Properties();
		// 此处配置的是kafka的端口
		props.put("metadata.broker.list", "10.9.242.53:9092");

		// 配置value的序列化类
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		// 配置key的序列化类
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");

		// request.required.acks
		// 0, which means that the producer never waits for an acknowledgement
		// from the broker (the same behavior as 0.7). This option provides the
		// lowest latency but the weakest durability guarantees (some data will
		// be lost when a server fails).
		// 1, which means that the producer gets an acknowledgement after the
		// leader replica has received the data. This option provides better
		// durability as the client waits until the server acknowledges the
		// request as successful (only messages that were written to the
		// now-dead leader but not yet replicated will be lost).
		// -1, which means that the producer gets an acknowledgement after all
		// in-sync replicas have received the data. This option provides the
		// best durability, we guarantee that no messages will be lost as long
		// as at least one in sync replica remains.
		props.put("request.required.acks", "-1");

		producer = new Producer<String, String>(new ProducerConfig(props));
	}

	@SuppressWarnings("deprecation")
	void produce() {
		// int messageNo = 1000;
		// final int COUNT = 10000;

		// while (messageNo < COUNT) {
		// String key = String.valueOf(messageNo);
		// String data = "hello kafka message " + key;
		// producer.send(new KeyedMessage<String, String>(TOPIC, key ,data));
		// System.out.println(data);
		// messageNo ++;
		// }

		File file = new File("/Users/tanyanbing/Desktop/zuobiao.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			// long l = 0l;
			// StringBuffer sbBuffer = new StringBuffer();
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				if (!tempString.trim().equals("")) {
					producer.send(new KeyedMessage<String, String>(TOPIC,tempString));
				}
				line++;
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	public static void main(String[] args) {
		new AddCoordinateData().produce();
	}
}
