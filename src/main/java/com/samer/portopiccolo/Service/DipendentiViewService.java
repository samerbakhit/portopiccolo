package com.samer.portopiccolo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samer.portopiccolo.DipendentiRepository.DipendentiViewRepository;
import com.samer.portopiccolo.modelli.Dipendenti;
import com.samer.portopiccolo.modelli.DipendentiView;

@Service
public class DipendentiViewService {

	@Autowired
	DipendentiViewRepository dvr ;
public long dipendentiConteggio() {
	return dvr.count();
}
public ArrayList<DipendentiView> tuttiDipendenti(){
	return (ArrayList<DipendentiView>) dvr.findAll(Sort.by(Sort.Direction.DESC,"codiceDipendente"));
}
public ArrayList<DipendentiView> findByCognome(String cognome) {
return dvr.findByCognome(cognome);
	
}
public ArrayList<DipendentiView> findBySettore(String settore) {
return dvr.findBySettore(settore);
	
}
}
