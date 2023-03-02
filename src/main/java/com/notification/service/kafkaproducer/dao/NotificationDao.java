package com.notification.service.kafkaproducer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notification.service.kafkaproducer.model.Notification;

public interface NotificationDao extends JpaRepository<Notification, Long>{
	
	 @Query(value = "select n.id_notif,\r\n"
	 		+ "n.date_notif,\r\n"
	 		+ "n.libelle_notif,rep_chef,\r\n"
	 		+ "n.nom,\r\n"
	 		+ "n.type_notif,\r\n"
	 		+ "n.cod_soc,\r\n"
	 		+ "(select p.nom_pers||' '||p.pren_pers from personnel p where p.cod_soc=n.cod_soc and p.mat_pers=n.id_reciver) libnomprenom,\r\n"
	 		+ "n.mat_pers,\r\n"
	 		+ "n.id_sender,\r\n"
	 		+ "n.id_reciver,\r\n"
	 		+ "n.counter,n.etat_notif,n.etat_notif_agent\r\n"
	 		+ "  from notification_portail n ,personnel p\r\n"
	 		+ "  where n.cod_soc = p.cod_soc \r\n"
	 		+ "  and n.mat_pers = p.mat_pers\r\n"
	 		+ "   and (get_pere_by_pere(n.cod_soc,p.cod_serv,:codServ)=:codServ or p.mat_pers=:matChef "
	 		+ "and n.id_reciver=:matChef) and n.etat_notif='N' \r\n", nativeQuery = true)
	  public List<Notification>   getNotif(@Param("matChef") String matChef,@Param("codServ") String codServ);
	 
	 @Query(value = "select n.id_notif,\r\n"
		 		+ "n.date_notif,rep_chef,\r\n"
		 		+ "n.libelle_notif,\r\n"
		 		+ "n.nom,\r\n"
		 		+ "n.type_notif,\r\n"
		 		+ "n.cod_soc,\r\n"
		 		+ "n.mat_pers,\r\n"
		 		+ "(select p.nom_pers||' '||p.pren_pers from personnel p where p.cod_soc=n.cod_soc and p.mat_pers=n.id_reciver) libnomprenom,\r\n"
		 		+ "n.id_sender,\r\n"
		 		+ "n.id_reciver,\r\n"
		 		+ "n.counter,n.etat_notif,n.etat_notif_agent\r\n"
		 		+ "  from notification_portail n ,personnel p\r\n"
		 		+ "  where n.cod_soc = p.cod_soc \r\n"
		 		+ "  and n.mat_pers = p.mat_pers\r\n"
		 		+ "   and n.mat_pers=:matPers and n.cod_soc=:codSoc and n.etat_notif_agent='N' \r\n", nativeQuery = true)
		  public List<Notification>   getNotifAgent(@Param("matPers") String matPers,@Param("codSoc") String codSoc);
	 
	 
	 @Query(value = "select count(counter)\r\n"
		 		+ "  from notification_portail  \r\n"
		 		+ "  where cod_soc=:codSoc and  id_reciver=:mat ", nativeQuery = true)
		  public Long   getcounter(@Param("mat") String matChef, @Param("codSoc") String codSoc);
	 
	 
	 
	 @Query(value = "select n.id_notif,\r\n"
		 		+ "n.date_notif,rep_chef,\r\n"
		 		+ "n.libelle_notif,\r\n"
		 		+ "n.nom,\r\n"
		 		+ "n.type_notif,\r\n"
		 		+ "n.cod_soc,\r\n"
		 		+ "n.mat_pers,\r\n"
		 		+ "n.id_sender,\r\n"
		 		+ "(select p.nom_pers||' '||p.pren_pers from personnel p where p.cod_soc=n.cod_soc and p.mat_pers=n.id_reciver) libnomprenom,\r\n"
		 		+ "n.id_reciver,\r\n"
		 		+ "n.counter,n.etat_notif,n.etat_notif_agent\r\n"
		 		+ "  from notification_portail n \r\n"
		 		+ " where n.id_reciver=:mat and n.etat_notif='N' \r\n", nativeQuery = true)
		  public List<Notification>   getNotifByMat(@Param("mat") String matChef);
	 
	 
	 @Query(value = "select n.id_notif,\r\n"
	 		+ "n.date_notif,rep_chef,\r\n"
	 		+ "n.libelle_notif,\r\n"
	 		+ "n.nom,\r\n"
	 		+ "n.type_notif,\r\n"
	 		+ "n.cod_soc,\r\n"
	 		+ "n.mat_pers,\r\n"
	 		+ "n.id_sender,\r\n"
	 		+ "(select p.nom_pers||' '||p.pren_pers from personnel p where p.cod_soc=n.cod_soc and p.mat_pers=n.id_reciver) libnomprenom,\r\n"
	 		+ "n.id_reciver,\r\n"
	 		+ "n.counter,n.etat_notif,n.etat_notif_agent\r\n"
	 		+ "  from notification_portail n \r\n"
	 		+ " where\r\n"
	 		+ " id_notif in ( select id_notif from notification_portail where  type_notif in ('PretAvance','Situation','Document') and etat_notif='N') \r\n"
	 		+ " or \r\n"
	 		+ " id_notif in ( select id_notif from notification_portail where  type_notif in ('Formation','Conge','Autorisation') and rep_chef='O')  ", nativeQuery = true)
		  public List<Notification>  getNotifRh();
		 
	 
	 
	 @Query(value = "select n.id_notif,\r\n"
	 		+ "n.date_notif,rep_chef,\r\n"
	 		+ "n.libelle_notif,\r\n"
	 		+ "n.nom,\r\n"
	 		+ "n.type_notif,\r\n"
	 		+ "n.cod_soc,\r\n"
	 		+ "n.mat_pers,\r\n"
	 		+ "(select p.nom_pers||' '||p.pren_pers from personnel p where p.cod_soc=n.cod_soc and p.mat_pers=n.id_reciver) libnomprenom,\r\n"
	 		+ "n.id_sender,\r\n"
	 		+ "n.id_reciver,\r\n"
	 		+ "n.counter,n.etat_notif,n.etat_notif_agent\r\n"
	 		+ "  from notification_portail n \r\n"
	 		+ " where n.type_notif='PretAvance'", nativeQuery = true)
		  public List<Notification>  getNotifRdddh();
	 @Query(value = "select n.id_notif,\r\n"
		 		+ "n.date_notif,rep_chef,\r\n"
		 		+ "n.libelle_notif,\r\n"
		 		+ "n.nom,\r\n"
		 		+ "n.type_notif,\r\n"
		 		+ "n.cod_soc,\r\n"
		 		+ "n.mat_pers,\r\n"
		 		+ "n.id_sender,\r\n"
		 		+ "n.id_reciver,\r\n"
		 		+ "(select p.nom_pers||' '||p.pren_pers from personnel p where p.cod_soc=n.cod_soc and p.mat_pers=n.id_sender) libnomprenom,\r\n"
		 		+ "n.counter,n.etat_notif,n.etat_notif_agent\r\n"
		 		+ "  from notification_portail n \r\n"
		 		+ " where n.cod_soc=:cod and n.id_reciver=:matpers", nativeQuery = true)
	 public List <Notification> gethistoriquenotification(@Param("matpers")String mat,@Param("cod") String cod );
	 @Query(value = "select n.id_notif,\r\n"
		 		+ "n.date_notif,rep_chef,\r\n"
		 		+ "n.libelle_notif,\r\n"
		 		+ "n.nom,\r\n"
		 		+ "n.type_notif,\r\n"
		 		+ "n.cod_soc,\r\n"
		 		+ "n.mat_pers,\r\n"
		 		+ "n.id_sender,\r\n"
		 		+ "n.id_reciver,\r\n"
		 		+ "(select p.nom_pers||' '||p.pren_pers from personnel p where p.cod_soc=n.cod_soc and p.mat_pers=n.id_sender) libnomprenom,\r\n"
		 		+ "n.counter,n.etat_notif,n.etat_notif_agent\r\n"
		 		+ "  from notification_portail n \r\n",nativeQuery = true)
	 public List <Notification> getall();
		 
}
