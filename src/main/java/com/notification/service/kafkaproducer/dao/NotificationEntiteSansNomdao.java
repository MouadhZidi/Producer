package com.notification.service.kafkaproducer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notification.service.kafkaproducer.model.NotificationEntiteSansNom;

public interface NotificationEntiteSansNomdao extends JpaRepository<NotificationEntiteSansNom, Long> {

}
