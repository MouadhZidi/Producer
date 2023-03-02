package com.notification.service.kafkaproducer.web;

import com.notification.service.kafkaproducer.dao.NotificationDao;
import com.notification.service.kafkaproducer.dao.NotificationEntiteSansNomdao;
import com.notification.service.kafkaproducer.model.Notification;
import com.notification.service.kafkaproducer.model.NotificationEntiteSansNom;
import com.notification.service.kafkaproducer.service.NotificationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
public class NotificationResource {

    private final NotificationService notificationService;
@Autowired
NotificationDao dao;
@Autowired
NotificationEntiteSansNomdao dao2;
    public NotificationResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotification(@RequestBody Notification notification) {
        notificationService.send(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   @GetMapping("/findall")
    public List<Notification> getall() {
	return dao.getall();
       
       
  }
    
	@GetMapping("/getnotif/{matChef}/{codServ}")
	public List<Notification> GETALLBY( @PathVariable("matChef") String matChef,@PathVariable("codServ") String codServ) {
		return dao.getNotif(matChef,codServ);
	}
	
	@GetMapping("/getnotifByMat/{Mat}")
	public List<Notification> GETALLBYMAT( @PathVariable("Mat") String Mat) {
		return dao.getNotifByMat(Mat);
	}
	@GetMapping("/getnotifRh")
	public List<Notification> GETALLRh() {
		return dao.getNotifRh();
	}
	
	@GetMapping("/getnotifRhd")
	public List<Notification> GETALLRhd() {
		return dao.getNotifRdddh();
	}
	@GetMapping("/getnotifAgent/{matPers}/{codSoc}")
	public List<Notification> GetNotifAgent( @PathVariable("matPers") String matPers,@PathVariable("codSoc") String codSoc) {
		return dao.getNotifAgent(matPers,codSoc);
	}
	
	@GetMapping("/getMax/{mat}/{codSoc}")
	public Long getMax(@PathVariable("mat") String mat,@PathVariable("codSoc") String codSoc) {
		return this.dao.getcounter(mat, codSoc);
	}
	
	
   
	 @CrossOrigin
	  @PutMapping("/updateEtatNotif")
		 
	  public ResponseEntity<NotificationEntiteSansNom> updateUclt( @RequestBody Notification Ag) {
	  
	     Optional<NotificationEntiteSansNom> AgData = dao2.findById(Ag.getId_notif());
	    
	     System.out.println(Ag.getId_notif());
	    if (AgData.isPresent()) {
	    	NotificationEntiteSansNom agg = AgData.get();
	   agg.setEtat_notif("O");
	      
	     return new ResponseEntity<>(dao2.save(agg), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } }
	 
	 @CrossOrigin
	  @PutMapping("/updateEtatNotifChef")
		 
	  public ResponseEntity<Notification> updateEtatNotifAgentChef( @RequestBody Notification Ag) {
	  
	     Optional<Notification> AgData = dao.findById(Ag.getId_notif());
	     System.out.println(Ag.getId_notif());
	    if (AgData.isPresent()) {
	    	Notification agg = AgData.get();
	   agg.setEtat_notif_agent("O");
	      
	     return new ResponseEntity<>(dao.save(agg), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } }
	 
	 
	 @CrossOrigin
	  @PutMapping("/updateEtatNotifAgent")
		 
	  public ResponseEntity<Notification> updateEtatNotifAgent( @RequestBody Notification Ag) {
	  
	     Optional<Notification> AgData = dao.findById(Ag.getId_notif());
	     System.out.println(Ag.getId_notif());
	    if (AgData.isPresent()) {
	    	Notification agg = AgData.get();
	   agg.setEtat_notif_agent("N");
	      
	     return new ResponseEntity<>(dao.save(agg), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } }
	 @GetMapping("/gethistoriquenotification/{mat}/{cod}")
	 public List <Notification> gethistoriquenotification(@PathVariable("mat")String mat,@PathVariable("cod")String cod){
		return dao.gethistoriquenotification(mat, cod);
		 
		 
		 
	 }
}
