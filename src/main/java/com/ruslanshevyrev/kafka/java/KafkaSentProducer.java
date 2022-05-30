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
		//Properties props = ProducerConfiguration
		//		.createProducer("10.191.209.208:9092,10.191.209.163:9092,10.191.209.157:9092", "clientid1");

		//props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		//Producer<Long, String> producer = new KafkaProducer<Long, String>(props);
		
                Properties kafkaProps = new Properties();

                kafkaProps.put("bootstrap.servers", "localhost:9092");
                kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
                kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
                KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);

                ProducerRecord<String, String> record =
                new ProducerRecord<>("kafkaTest", "test","From Test1");
                try {
                    producer.send(record).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
	}
}
