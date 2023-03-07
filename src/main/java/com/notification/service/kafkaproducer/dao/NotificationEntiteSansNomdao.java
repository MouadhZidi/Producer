package com.notification.service.kafkaproducer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notification.service.kafkaproducer.model.Notification;
import com.notification.service.kafkaproducer.model.NotificationEntiteSansNom;

public interface NotificationEntiteSansNomdao extends JpaRepository<NotificationEntiteSansNom, Long> {
	 @Query(value = "select count(counter)\r\n"
		 		+ "  from notification_portail  \r\n"
		 		+ "  where cod_soc=:codSoc and  id_reciver=:mat ", nativeQuery = true)
		  public Long   getcounter(@Param("mat") String matChef, @Param("codSoc") String codSoc);

}
