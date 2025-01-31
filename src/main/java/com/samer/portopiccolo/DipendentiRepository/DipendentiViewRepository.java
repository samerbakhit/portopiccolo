package com.samer.portopiccolo.DipendentiRepository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samer.portopiccolo.modelli.DipendentiView;

public interface DipendentiViewRepository extends JpaRepository<DipendentiView,Long> {
long count();
ArrayList<DipendentiView>findByCognome(String cognome);
ArrayList<DipendentiView>findBySettore(String settore);	
}
