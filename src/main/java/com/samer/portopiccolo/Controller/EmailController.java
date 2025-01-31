package com.samer.portopiccolo.Controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samer.portopiccolo.Service.DipendentiService;
import com.samer.portopiccolo.Service.DipendentiViewService;
import com.samer.portopiccolo.Service.EmailService;
import com.samer.portopiccolo.Service.SettoreService;
import com.samer.portopiccolo.modelli.EmailRequest;
import com.samer.portopiccolo.modelli.Settore;

@Controller
public class EmailController {
	@Autowired
	SettoreService ss;
	@Autowired
	DipendentiService ds;
	@Autowired
	DipendentiViewService dvs;
    @Autowired
    private EmailService emailService;

   
    
    @GetMapping("emails")
    public String emailsShow(Model model) {
		Long ListaDipendenti = dvs.dipendentiConteggio();
	    model.addAttribute("lDipendenti", ListaDipendenti);
	   
	    model.addAttribute("settore", new Settore());
	    model.addAttribute("settoreFiltro", new Settore()); // Inizializza il bean settore
	    model.addAttribute("settoreList", ss.mostraSettore());
	    model.addAttribute("pageTitle", "Lista Dipendenti");
	    model.addAttribute("countSettore",ss.contaSettore());
	    ArrayList<EmailRequest>ListaEmail=emailService.AllEmails();
	    model.addAttribute("ListaEmail", ListaEmail);
    	return ("/Emails/emails");
    }
    @GetMapping("/email")
    public String showContactForm(Model model) {
    	 long ListaDipendenti = dvs.dipendentiConteggio();
    	model.addAttribute("countSettore",ss.contaSettore());
    	model.addAttribute("lDipendenti", ListaDipendenti);
        model.addAttribute("emailRequest", new EmailRequest());
        model.addAttribute("settoreFiltro", new Settore());
        model.addAttribute("settoreList", ss.mostraSettore());
        boolean newRecordAdded=false;
        return "contact"; // Nome del file Thymeleaf (contact.html)
    }

    @PostMapping("/email/send")
    
    public String sendEmail(
    		@Valid @ModelAttribute EmailRequest emailRequest,
    		BindingResult bindingResult,
    		Model model,
    		 HttpSession session
    		) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("settoreFiltro", new Settore());
        	long ListaDipendenti = dvs.dipendentiConteggio();
         	model.addAttribute("countSettore",ss.contaSettore());
         	model.addAttribute("lDipendenti", ListaDipendenti);
         	model.addAttribute("settoreList", ss.mostraSettore());
         	boolean newRecordAdded=false;
            return "contact";
        }
   	 long ListaDipendenti = dvs.dipendentiConteggio();
   	model.addAttribute("settoreList", ss.mostraSettore());
	 model.addAttribute("countSettore",ss.contaSettore());
	 model.addAttribute("lDipendenti", ListaDipendenti);
     model.addAttribute("settoreFiltro", new Settore());
     model.addAttribute("successMessage", "Email Sent Correctly");
     model.addAttribute("emailRequest", new EmailRequest());
   
     String content="Dear "+emailRequest.getName()+" \n\n"+
     "<h4>your origin message :<u>"+emailRequest.getSubject()+"</u><br>"+emailRequest.getMessage()+"</h4></u>"+
     "We will call you soon\n"+
     "Best Reguards\n"+
     "SamerSoft Corporation";
     emailService.saveEmail(emailRequest);  
     
     emailService.sendEmail(emailRequest.getEmail(),emailRequest.getSubject(), content);
     
//     model.addAttribute("newRecordAdded", true);
//     session.setAttribute("newRecordAdded", true);
//     System.out.println("newRecordAdded: " + model.getAttribute("newRecordAdded"));
     return "contact";
    }
}