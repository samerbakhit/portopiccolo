package com.samer.portopiccolo.modelli;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="dipendenti")
public class Dipendenti {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codiceDipendente;
	@NotBlank(message="Bisogna inserire il nome")
	private String nome;
	@NotBlank(message="Bisogna inserire il cognome")
	private String cognome;
	@NotBlank(message="Bisogna inserire l' email")
	@Email(message="Formato errato")
	@Pattern(
	        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
	        message = "Il dominio dell' email non è valido."
	    )
	private String email;
	@NotBlank(message="Bisogna inserire il cellulare")
	@Size(min = 8, max = 15, message = "La lunghezza deve essere tra 8 e 15 caratteri.")
	private String cell;
	@NotNull(message="Bisogna inserire il paese di cittadinanza")
	private Integer Cittadinanza;
	@NotNull(message="Bisogna Inserire il settore di lavoro")
	private Integer settore;
	
	public Integer getSettore() {
		return settore;
	}
	public void setSettore(Integer settore) {
		this.settore = settore;
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

	public Dipendenti() {
		super();
	}
	public Integer getCittadinanza() {
		return Cittadinanza;
	}
	public void setCittadinanza(Integer cittadinanza) {
		Cittadinanza = cittadinanza;
	}
	
	
	
	
}
