package com.lesstraffic.geolocationservice.config;

import com.lesstraffic.geolocationservice.model.Geolocation;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerConfiguration {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
	
	@Value("${lesstraffic.geolocation.group.insert-node}")
	private String GEOLOCATION_GROUP_INSERT_NODE;
    
    @Bean
    public Map<String, Object> consumerConfigs(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    properties.put(ConsumerConfig.GROUP_ID_CONFIG, GEOLOCATION_GROUP_INSERT_NODE);
	    
        return properties;
    }

    @Bean
    public ConsumerFactory<String, Geolocation> consumerFactory(){
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
			    new JsonDeserializer<>(Geolocation.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Geolocation> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, Geolocation> factory =
			    new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    
	    return factory;
    }
}
