package netgloo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.models.User;
import netgloo.models.UserDao;
import netgloo.service.MailService;
import netgloo.serviceimpl.Mail;

@RestController
public class MainController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserDao userDao;
	
	 private final Logger log = LoggerFactory.getLogger(this.getClass());


  @RequestMapping(value="/sendMail",method=RequestMethod.GET)
  public String sendMail() {
	  Mail mail = new Mail();
      mail.setMailFrom("pravin14apr@gmail.com");
      mail.setMailTo("Ananthraman.Parasuraman@mastek.com");
      mail.setMailSubject("Spring 4 - Email with velocity template pot");
      mail.setTemplateName("email.vm");
      List < Object > attachments = new ArrayList < Object > ();
      attachments.add(new ClassPathResource("dog.jpg"));
      attachments.add(new ClassPathResource("cat.jpg"));
      mail.setAttachments(attachments);
      mailService.sendEmail(mail);
    return "Mail Sent";
  }
  @RequestMapping(value="/user",method=RequestMethod.GET)
  public User createUser() 
  {	  User user= new User();
	  try {
		    log.debug("debug level log");
		    log.info("info level log");
	
		  user.setName("pravin");
		  user.setEmail("prav");
		  user=userDao.save(user);
		  
	} catch (Exception e) {
	    log.error("error level log"+e);
	}
	  return user;
  }
}
