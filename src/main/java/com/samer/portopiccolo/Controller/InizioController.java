package com.samer.portopiccolo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.samer.portopiccolo.Service.DipendentiService;
import com.samer.portopiccolo.Service.EmailService;
import com.samer.portopiccolo.Service.SettoreService;
import com.samer.portopiccolo.modelli.Dipendenti;
import com.samer.portopiccolo.modelli.Settore;

@Controller
public class InizioController {

	@Autowired
	EmailService es;
	@Autowired
	DipendentiService dvs;
	@Autowired 
	SettoreService ss;
	@GetMapping({"","/"})
	public String inizia(Model model) {
			Long ListaDipendenti = dvs.dipendentiConteggio();
		    model.addAttribute("lDipendenti", ListaDipendenti);
		  
		    model.addAttribute("settore", new Settore());
		    model.addAttribute("settoreFiltro", new Settore()); // Inizializza il bean settore
		    model.addAttribute("settoreList", ss.mostraSettore());
		    model.addAttribute("pageTitle", "Lista Dipendenti");
		    model.addAttribute("countSettore",ss.contaSettore());
		    Long emailC = es.emailConteggio();
		    model.addAttribute("emailC", emailC);
		    System.out.println("emails: "+emailC);
	  // Passa il frammento dipendenti
		   
		    return ("index"); // Restituisce la pagina principale (index.html)
	}
	@GetMapping("/test")
	public String test(Model model) {
		 Long ListaDipendenti = dvs.dipendentiConteggio();
		 model.addAttribute("countSettore",ss.contaSettore());
		    model.addAttribute("lDipendenti", ListaDipendenti);
		
		 model.addAttribute("settore", new Settore()); 
		 model.addAttribute("settoreFiltro", new Settore());// Inizializza il bean settore
		    model.addAttribute("settoreList", ss.mostraSettore());
		return("Tests/test");
	}


	
}
