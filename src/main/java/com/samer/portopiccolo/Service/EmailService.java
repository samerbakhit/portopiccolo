package com.samer.portopiccolo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.samer.portopiccolo.DipendentiRepository.EmailRepository;
import com.samer.portopiccolo.modelli.Dipendenti;
import com.samer.portopiccolo.modelli.EmailRequest;



	@Service
	public class EmailService {
	@Autowired
	private EmailRepository er;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${emails.sender_email}")
    private String senderEmail;
    @Value("${emails.sender_name}")
	private String senderName;
    @Value("${emails.sender_address}")
	private String senderAddress;
   public void sendEmail(String recieverEmail, String subject, String content)
   {
	   try 
	   {
		  SimpleMailMessage message= new SimpleMailMessage() ;
		   message.setTo(recieverEmail);
		   message.setFrom(senderEmail);
		   message.setSubject(subject);
		  
		   message.setText(content);
		   mailSender.send(message);
		   System.out.println("Email sent correct");
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
		   
	   }
    		
   }
   public Long emailConteggio() {
	   return er.count();
   }
   public void saveEmail(EmailRequest emailRequest) {


	    if (emailRequest.getId()!= null) {
	        Optional<EmailRequest> existingUser = er.findById(emailRequest.getId());
	        if (existingUser.isPresent()) {
	        	EmailRequest updatedUser = existingUser.get();
	            updatedUser.setName(senderName);
	            updatedUser.setSubject(emailRequest.getSubject());
	            updatedUser.setMessage(emailRequest.getMessage());
	            updatedUser.setEmail(emailRequest.getEmail());
	          
	           er.save(updatedUser);
	            
	        }
	    }
	    er.save(emailRequest);
	}
   public ArrayList<EmailRequest>AllEmails(){
	   return (ArrayList<EmailRequest>) er.findAll(Sort.by(Sort.Direction.DESC,"Id"));
   }
 
}