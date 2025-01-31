package com.samer.portopiccolo.modelli;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="paesi")
public class Paesi {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long idPaese;
private String paese;
public Long getIdPaese() {
	return idPaese;
}
public void setIdPaese(Long idPaese) {
	this.idPaese = idPaese;
}
public String getPaese() {
	return paese;
}
public void setPaese(String paese) {
	this.paese = paese;
}

}
