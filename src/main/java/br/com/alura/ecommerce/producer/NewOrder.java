package br.com.alura.ecommerce.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrder {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

       KafkaProducer producer = new KafkaProducer<String, String >(properties());
       String key = "123";
       String value = "990,00";
       ProducerRecord record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", key, value);
       producer.send(record, (data, ex) ->{
           if (ex != null){
               ex.printStackTrace();
               return;
           }else{
               System.out.println("Tópico: " + data.topic() + " - " + "Partição: " + data.partition() + "Offset: " +
                       data.offset() + "Time: " + data.timestamp());
           }}).get();
    }

    private static Properties properties() {
        Properties properties = new Properties();

       properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1.9092");
       properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
       properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

       return properties;
    }
}
