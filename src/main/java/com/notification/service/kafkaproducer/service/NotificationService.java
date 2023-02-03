package com.notification.service.kafkaproducer.service;

import java.util.List;

import com.notification.service.kafkaproducer.model.Notification;

public interface NotificationService {

    /**
     * Send notification
     * @param notification model of notification
     */
    void send(Notification notification);
}
