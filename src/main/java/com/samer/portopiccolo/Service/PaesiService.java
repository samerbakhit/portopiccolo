package com.samer.portopiccolo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samer.portopiccolo.DipendentiRepository.PaesiRepository;
import com.samer.portopiccolo.modelli.Paesi;

@Service
public class PaesiService {
	@Autowired
	PaesiRepository pr;
	

	public ArrayList<Paesi> mostraPaesi() {
		return (ArrayList<Paesi>) pr.findAll();
		
	}
}
