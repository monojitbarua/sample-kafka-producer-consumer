package kafka.poc.gunjan;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {

	public static void main(String[] args) throws Exception {

		Properties properties = new Properties();
		properties.put("bootstrap.servers", "<give your ip>:9092");
		properties.put("kafka.topic", "<give your topic name>");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("max.partition.fetch.bytes", "2097152");
		properties.put("max.poll.records", "500");
		properties.put("group.id", "sample-group");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		try {
			consumer.subscribe(Arrays.asList(properties.getProperty("kafka.topic")));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf("partition = %s, offset = %d, key = %s, value = %s\n", record.partition(),
							record.offset(), record.key(), record.value());
				}
			}
		}
		finally {
			consumer.close();
		}
	}

}
