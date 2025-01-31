package com.samer.portopiccolo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samer.portopiccolo.DipendentiRepository.SettoreRepository;

import com.samer.portopiccolo.modelli.Settore;

@Service
public class SettoreService {

	@Autowired
	SettoreRepository sr;
	
	public Long contaSettore() {
		return sr.count();
	}
	
	public ArrayList<Settore>mostraSettore(){
		return (ArrayList<Settore>) sr.findAll(Sort.by(Sort.Direction.DESC,"idSettore"));
		
	}


	public Settore findByIdSettore(Long idSettore) {
	return sr.findById(idSettore).orElse(null);
		
	}
	public void saveSettore(Settore settore) {


	    if (settore.getIdSettore()!= null) {
	        Optional<Settore> existingSettore = sr.findById(settore.getIdSettore());
	        if (existingSettore.isPresent()) {
	            Settore updatedSettore = existingSettore.get();
	            updatedSettore.setSettore(settore.getSettore());
	      System.out.println(settore);
	           sr.save(updatedSettore);
	         
	        }
	    }
	    System.out.println(settore);
	    sr.save(settore);
	}
	

	public void deleteSettore(Long idSettore) {
		sr.deleteById(idSettore);
	}

	
	
	
}
