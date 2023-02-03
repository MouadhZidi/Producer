package com.notification.service.kafkaproducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.service.kafkaproducer.dao.NotificationDao;
import com.notification.service.kafkaproducer.exception.MapperException;
import com.notification.service.kafkaproducer.model.Notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final BrokerProducerService brokerProducerService;
    private final Environment env;
@Autowired 
NotificationDao dao;
    public NotificationServiceImpl(BrokerProducerService brokerProducerService, Environment env) {
        this.brokerProducerService = brokerProducerService;
        this.env = env;
    }

    @Override
    public void send(Notification notification) {
    	Long max = this.dao.getcounter(notification.getId_receiver(), notification.getCod_soc());  
    	System.out.println(notification.getId_receiver());
    	notification.setCounter(max+1);
    	brokerProducerService.sendMessage(env.getProperty("producer.kafka.topic-name"), toJson(notification));
        
        this.dao.save(notification);

    }


    /**
     * Convert Object to json
     *
     * @param object object
     * @return string json
     */
    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }

	
}
