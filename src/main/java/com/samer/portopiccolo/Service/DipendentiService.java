package com.samer.portopiccolo.Service;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.samer.portopiccolo.DipendentiRepository.DipendentiRepository;
import com.samer.portopiccolo.modelli.Dipendenti;

@Service
public class DipendentiService {
	
@Autowired
	DipendentiRepository dr ;
public long dipendentiConteggio() {
	return dr.count();
}
public ArrayList<Dipendenti> tuttiDipendenti(){
	return (ArrayList<Dipendenti>) dr.findAll(Sort.by(Sort.Direction.DESC,"codiceDipendente"));
}

public Dipendenti findByCodiceDipendente(Long codiceDipendente) {
return dr.findById(codiceDipendente).orElse(null);
	
}
public void saveUser(Dipendenti dipendente) {


    if (dipendente.getCodiceDipendente()!= null) {
        Optional<Dipendenti> existingUser = dr.findById(dipendente.getCodiceDipendente());
        if (existingUser.isPresent()) {
            Dipendenti updatedUser = existingUser.get();
            updatedUser.setNome(dipendente.getNome());
            updatedUser.setCognome(dipendente.getCognome());
            updatedUser.setCittadinanza(dipendente.getCittadinanza());
            updatedUser.setSettore(dipendente.getSettore());
            updatedUser.setEmail(dipendente.getEmail());
            updatedUser.setCell(dipendente.getCell());
           dr.save(updatedUser);
            
        }
    }
    dr.save(dipendente);
}
public void deleteDipendente(Long codiceDipendente) {
	dr.deleteById(codiceDipendente);
}
public  ArrayList< Dipendenti> findByCognome(String cognome) {
	return dr.findByCognome(cognome);
}
}
