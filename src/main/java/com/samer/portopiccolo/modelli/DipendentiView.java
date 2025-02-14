package com.samer.portopiccolo.modelli;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dipendentiview")
public class DipendentiView {
	@Column(name = "codice_dipendente")
	@Id
private Long codiceDipendente;
private String nome;
private String cognome;
private String  email;
private String cell;

private int idPaese;
private String paese;
@Column(name = "id_settore")
private int idSettore;
private String settore;

public int getIdSettore() {
	return idSettore;
}
public void setIdSettore(int idSettore) {
	this.idSettore = idSettore;
}
public String getSettore() {
	return settore;
}
public void setSettore(String settore) {
	this.settore = settore;
}
public int getIdPaese() {
	return idPaese;
}
public void setIdPaese(int idPaese) {
	this.idPaese = idPaese;
}
public Long getCodiceDipendente() {
	return codiceDipendente;
}
public void setCodiceDipendente(Long codiceDipendente) {
	this.codiceDipendente = codiceDipendente;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCognome() {
	return cognome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCell() {
	return cell;
}
public void setCell(String cell) {
	this.cell = cell;
}
public String getPaese() {
	return paese;
}
public void setPaese(String paese) {
	this.paese = paese;
}
public DipendentiView(Long codiceDipendente, String nome, String cognome, String email, String cell, int idPaese,
		String paese, int idSettore, String settore) {
	super();
	this.codiceDipendente = codiceDipendente;
	this.nome = nome;
	this.cognome = cognome;
	this.email = email;
	this.cell = cell;
	this.idPaese = idPaese;
	this.paese = paese;
	this.idSettore = idSettore;
	this.settore = settore;
}
public DipendentiView() {
	super();
}


	
}
