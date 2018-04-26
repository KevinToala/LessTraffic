package com.lesstraffic.queueconsumer.config;

import com.lesstraffic.queueconsumer.entities.Event;
import com.lesstraffic.queueconsumer.listeners.QueueMessageListener;
import com.lesstraffic.queueconsumer.repositories.EventRepository;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerConfiguration {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Autowired private QueueMessageListener queueMessageListener;
    @Autowired private EventRepository eventRepository;

    @Bean
    public Map<String, Object> consumerConfigs(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "GENERIC_GROUP");

        return properties;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setMessageConverter(new StringJsonMessageConverter());

        return factory;
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer(){
        String[] topics = eventRepository.findAll()
                .map(Event::getTopic)
                .toArray(String[]::new);

        ContainerProperties containerProperties = new ContainerProperties(topics);
        containerProperties.setMessageListener(queueMessageListener);
        return new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties);
    }
}
