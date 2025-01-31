package com.samer.portopiccolo.DipendentiRepository;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import com.samer.portopiccolo.modelli.Dipendenti;

public interface DipendentiRepository extends JpaRepository<Dipendenti,Long> {

	long count();
	ArrayList<Dipendenti>findByCognome(String cognome);
	
}
