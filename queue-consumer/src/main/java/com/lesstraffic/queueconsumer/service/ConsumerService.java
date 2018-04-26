package com.lesstraffic.queueconsumer.service;

import com.lesstraffic.queueconsumer.dto.GeolocationDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
    //@KafkaListener(topics = "${lesstraffic.geolocation.topic.enqueue-node}")
    public void genericEnqueue(GeolocationDTO geolocation){
	    Application application = eurekaClient.getApplication("gateway-server");
	    InstanceInfo instanceInfo = application.getInstances().get(0);
	    
	    GeolocationDTO newGeolocation = restTemplate.postForObject(
	    		"http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/api/geolocation/insertNode",
			    geolocation, GeolocationDTO.class
	    );
	    
        System.out.println(newGeolocation);
    }
}
