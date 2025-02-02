package com.samer.portopiccolo.Controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samer.portopiccolo.Service.DipendentiService;
import com.samer.portopiccolo.Service.DipendentiViewService;
import com.samer.portopiccolo.Service.PaesiService;

import com.samer.portopiccolo.Service.SettoreService;
import com.samer.portopiccolo.modelli.Dipendenti;
import com.samer.portopiccolo.modelli.DipendentiView;
import com.samer.portopiccolo.modelli.Paesi;
import com.samer.portopiccolo.modelli.Settore;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/dipendenti")
public class DipendentiController {

	@Autowired
	DipendentiService ds;
	@Autowired
	DipendentiViewService dvs;
	@Autowired
	PaesiService ps;
	@Autowired
	SettoreService ss;
	
	
	@GetMapping({"","/"})
	public String stampa(
			Model model
			) {
		 long ListaDipendenti = dvs.dipendentiConteggio();
		 model.addAttribute("countSettore",ss.contaSettore());
		 model.addAttribute("lDipendenti", ListaDipendenti);
		ArrayList<DipendentiView>Lista =dvs.tuttiDipendenti();
		 model.addAttribute("orderColumn", 0); // Imposta la colonna su cui ordinare (indice della colonna)
		    model.addAttribute("orderDir", "desc");
		model.addAttribute("Lista", Lista);
		long count = dvs.dipendentiConteggio();
		model.addAttribute("count", count);
		 model.addAttribute("settore", new Settore()); 
		 // Inizializza il bean settore
		 model.addAttribute("settoreList", ss.mostraSettore());
		 model.addAttribute("settoreFiltro", new Settore());
		return ("Dipendenti/dipendenti");
				
	}
	

	
	
	
	
	@GetMapping("edit/{codiceDipendente}")
	public String dipendentiForm(
			@PathVariable Long codiceDipendente,
			Model model
			) {
		 long ListaDipendenti = dvs.dipendentiConteggio();
		 model.addAttribute("countSettore",ss.contaSettore());
		 model.addAttribute("lDipendenti", ListaDipendenti);
		Dipendenti dipendente = ds.findByCodiceDipendente(codiceDipendente);
		model.addAttribute("dipendente", dipendente);
		ArrayList<Paesi> paesiList = ps.mostraPaesi();
		model.addAttribute("paesiList", paesiList);
		ArrayList<Settore> settoreList = ss.mostraSettore();
		model.addAttribute("settoreList", settoreList);
		 model.addAttribute("settore", new Settore()); // Inizializza il bean settore
		    model.addAttribute("settoreList", ss.mostraSettore());
		    model.addAttribute("settoreFiltro", new Settore());
		long count = dvs.dipendentiConteggio();
		model.addAttribute("count", count);
		return("Dipendenti/dipendenti-form");
	}
	 @GetMapping("/new")
	    public String showCreateForm(
	    		Model model
	    		) {
		 long ListaDipendenti = dvs.dipendentiConteggio();
		 model.addAttribute("countSettore",ss.contaSettore());
		 	model.addAttribute("lDipendenti", ListaDipendenti);
		 	ArrayList<Paesi> paesiList = ps.mostraPaesi();
			model.addAttribute("paesiList", paesiList);
			ArrayList<Settore> settoreList = ss.mostraSettore();
			model.addAttribute("settoreList", settoreList);
	        model.addAttribute("dipendente", new Dipendenti());
	        model.addAttribute("settore", new Settore()); // Inizializza il bean settore
		    model.addAttribute("settoreList", ss.mostraSettore());
		    model.addAttribute("settoreFiltro", new Settore());
	     return ("Dipendenti/dipendenti-form");
	    }
	 
	 
	 
	 // Salva un nuovo utente con validazione
	    @PostMapping("/save")
	    public String saveUser(
	    		@Valid @ModelAttribute("dipendente") Dipendenti dipendente,
	    		BindingResult result,
	    		Model model
	    		
	    		) {
	        if (result.hasErrors()) {
	        	ArrayList<Paesi> paesiList = ps.mostraPaesi();
				model.addAttribute("paesiList", paesiList);
				ArrayList<Settore> settoreList = ss.mostraSettore();
				model.addAttribute("settoreList", settoreList);
				 long ListaDipendenti = dvs.dipendentiConteggio();
				 model.addAttribute("lDipendenti", ListaDipendenti);
				 model.addAttribute("settoreFiltro", new Settore());
				 model.addAttribute("settore", new Settore());
				 model.addAttribute("countSettore",ss.contaSettore());
	            return "Dipendenti/dipendenti-form";  // Ritorna al form se ci sono errori di validazione
	        }
	        long count = dvs.dipendentiConteggio();
	        model.addAttribute("countSettore",ss.contaSettore());
			model.addAttribute("count", count);
	    	model.addAttribute("dipendente", dipendente);
	    	model.addAttribute("settore", new Settore());
	    	model.addAttribute("settoreFiltro", new Settore());
	        ds.saveUser(dipendente);
	        return "redirect:/dipendenti";
	    }

	    @GetMapping("/delete/{codiceDipendente}")
	    public String deleteStudente
	    (
	    		@PathVariable Long codiceDipendente
	    		){
	    	
			ds.deleteDipendente(codiceDipendente);
			return "redirect:/dipendenti";
	    }
	    
	    @GetMapping("/cerca")
	    public String cercaStudente(
	                  				
	                  				@RequestParam("search")  String cognome,
	                                Model model) {
	    	 long ListaDipendenti = dvs.dipendentiConteggio();
	    	 model.addAttribute("countSettore",ss.contaSettore());
			 model.addAttribute("lDipendenti", ListaDipendenti);
			 model.addAttribute("settore", new Settore()); // Inizializza il bean settore
			    model.addAttribute("settoreList", ss.mostraSettore());
			    model.addAttribute("settoreFiltro", new Settore());
	     if ( cognome != null && !cognome.isEmpty()) {
	            // Ricerca per cognome
	        	
	    	 model.addAttribute("settore", new Settore()); // Inizializza il bean settore
			    model.addAttribute("settoreList", ss.mostraSettore());
	            ArrayList<DipendentiView> Lista = (ArrayList<DipendentiView>)dvs.findByCognome(cognome);
	            if(Lista!=null) {
	            	long count = dvs.dipendentiConteggio();
	        		model.addAttribute("count", count);
	                model.addAttribute("Lista", Lista);
	                model.addAttribute("count", Lista.size());
	            }else {
	            	 model.addAttribute("count", Lista.size());	
	            }
	            
	            
	           
	        } 
	        
	     return ("Dipendenti/dipendenti"); // Indica la pagina HTML che visualizza i risultati
	    }

	    

	    
	
}
