package com.ruslanshevyrev.kafka.java;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaSentProducer {

	public static void main(String[] args) {
		Properties props = ProducerConfiguration
				.createProducer("10.191.209.208:9092,10.191.209.163:9092,10.191.209.157:9092", "clientid1");

		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		Producer<Long, String> producer = new KafkaProducer<Long, String>(props);
		
		for (int index = 0; index < KafkaConstants.MESSAGE_COUNT; index++) {
			String event = "This is record " + index;
			String topicName = "demo-session4";
			ProducerRecord<Long, String> record = new ProducerRecord(topicName, event);
			//ProducerRecord<Long, String> record2 = new ProducerRecord("xyz-session4", event);
			
			try {
				Thread.sleep(1000);
				// System.out.println("called");
				RecordMetadata metadata = producer.send(record).get();
				
				//RecordMetadata metadata2 = producer.send(record2).get();
				
				/*
				 * //producer.send(record2).get(); System.out.println("Record sent with key " +
				 * index + " to partition " + metadata.partition() + " with offset " +
				 * metadata.offset()); //producer.send(record2).get();
				 * System.out.println("Record sent with key " + index + " to partition " +
				 * metadata2.partition() + " with offset " + metadata2.offset());
				 */
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (Exception e) {
				System.out.println("Error in sending record 2");
				System.out.println(e);
			}
		}
	}
}
