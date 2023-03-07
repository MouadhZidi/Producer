package com.notification.service.kafkaproducer.service;

import java.util.List;

import com.notification.service.kafkaproducer.model.Notification;
import com.notification.service.kafkaproducer.model.NotificationEntiteSansNom;

public interface NotificationService {

    /**
     * Send notification
     * @param notification model of notification
     */
    void send(NotificationEntiteSansNom notification);
}
