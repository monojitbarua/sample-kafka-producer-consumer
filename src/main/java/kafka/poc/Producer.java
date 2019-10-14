package kafka.poc;

import java.util.Properties;
import java.util.Random;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.google.gson.Gson;

public class Producer {

	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "<give your ip>:9092");
		properties.put("acks"             , "0");
		properties.put("batch.size"       , "20971520");
		properties.put("max.request.size" , "2097152");
		properties.put("key.serializer"   , "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("kafka.topic"      , "<give your topic name>");

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		try {
			while(true) {
				Thread.sleep(1000);
				producer.send(new ProducerRecord<String, String>(properties.getProperty("kafka.topic"), getEmployeeDataInJson()));
			}
		} finally {
			producer.close();
		}
	}

	//generating random employee object
	public static String getEmployeeDataInJson() throws Exception {
		Gson gson = new Gson();
		Employee emp = new Employee("Test Name "+getRandomNumberInRange(100,500), getRandomNumberInRange(10000,20000), getRandomNumberInRange(21,60));
		String json = gson.toJson(emp);
		System.out.println(json);
		return json;
	}

	//generating random number with in a given range (min and max)
	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();
	}	
}

