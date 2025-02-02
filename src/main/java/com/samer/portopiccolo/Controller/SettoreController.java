package com.samer.portopiccolo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samer.portopiccolo.Service.DipendentiViewService;
import com.samer.portopiccolo.Service.SettoreService;

import com.samer.portopiccolo.modelli.Settore;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/settori")
public class SettoreController {
	@Autowired
	SettoreService ss;
	@Autowired
	DipendentiViewService dvs;
	
	@GetMapping({"","/"})
	public String showSettori(Model model) {
		ArrayList<Settore>lista = ss.mostraSettore();
		 model.addAttribute("settoreList", lista); 
		 // Inizializza il bean settore
		 model.addAttribute("countSettore",ss.contaSettore());
		 model.addAttribute("settoreFiltro", new Settore());
		 Long ListaDipendenti = dvs.dipendentiConteggio();
		    model.addAttribute("lDipendenti", ListaDipendenti);
		return("Settori/settori");
	}
	
	@GetMapping("edit/{idSettore}")
	public String settoriForm(
			@PathVariable Long idSettore,
			Model model
			) {
		 long ListaDipendenti = dvs.dipendentiConteggio();
		 model.addAttribute("countSettore",ss.contaSettore());
		 model.addAttribute("lDipendenti", ListaDipendenti);
		Settore settore = ss.findByIdSettore(idSettore);
		model.addAttribute("settoreForm", settore);

	
		// Inizializza il bean settore
		
	 model.addAttribute("settoreFiltro", new Settore());
		long count = dvs.dipendentiConteggio();
		model.addAttribute("count", count);
		return("Settori/settore-forms");
	}
	@GetMapping("/new")
    public String showCreateForm(
    		Model model
    		) {
	 long ListaDipendenti = dvs.dipendentiConteggio();
	 model.addAttribute("countSettore",ss.contaSettore());
	 	model.addAttribute("lDipendenti", ListaDipendenti);

	
     
        model.addAttribute("settoreForm", new Settore());
	    model.addAttribute("settoreFiltro", new Settore());
     return ("Settori/settore-forms");
    }
	 @PostMapping("/save")
	    public String saveSettore(
	    		@Valid @ModelAttribute("settoreForm") Settore settoreForm,
	    		BindingResult result,
	    		Model model
	    		
	    		) {
	        if (result.hasErrors()) {
	        	
	        	 long ListaDipendenti = dvs.dipendentiConteggio();
				 model.addAttribute("lDipendenti", ListaDipendenti);
				 model.addAttribute("settoreFiltro", new Settore());
				 
				 model.addAttribute("countSettore",ss.contaSettore());
	        	
	        	
	        	
	        	model.addAttribute("settoreFiltro", new Settore());
	        	 
	            return "Settori/settore-forms";  // Ritorna al form se ci sono errori di validazione
	        }
	      
	        ss.saveSettore(settoreForm);
	        return "redirect:/settori";
	    }

	    @GetMapping("/delete/{idSettore}")
	    public String deleteStudente
	    (
	    		@PathVariable Long idSettore
	    		){
	    	
			ss.deleteSettore(idSettore);
			return "redirect:/settori";
	    }
	

}
