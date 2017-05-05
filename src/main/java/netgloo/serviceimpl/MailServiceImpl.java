package netgloo.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import netgloo.service.MailService;
@Service("mailService")
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Override
	public void sendEmail(Mail mail) {
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            for (Object attachment: mail.getAttachments()) {
                File file;
				try {
					file = ((ClassPathResource) attachment).getFile();
				    mimeMessageHelper.addAttachment(file.getName(), file);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
         
            }
            mail.setMailContent(geContentFromTemplate(mail));
            mimeMessageHelper.setText(mail.getMailContent(), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
        	
            e.printStackTrace();
        }
	}
	
    public String geContentFromTemplate(Mail mail) {
    	 StringWriter stringWriter = new StringWriter();
        try {
        	Template template = velocityEngine.getTemplate("./templates/email.vm");

        	  VelocityContext velocityContext = new VelocityContext();
        	  velocityContext.put("firstName", "Ananthraman");
        	  velocityContext.put("lastName", "Parasuraman");
        	  velocityContext.put("location", "Thane");
        	  velocityContext.put("signature", "www.mastek.com");
        	 
        	  
        	  template.merge(velocityContext, stringWriter);

        } catch (Exception e) {
        	logger.error("Error....************"+e);
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

}
