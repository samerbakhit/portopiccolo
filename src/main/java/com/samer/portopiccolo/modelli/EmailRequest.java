package com.samer.portopiccolo.modelli;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name="EmailRequest")
public class EmailRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Inserire il nome")
private String name;
	@NotBlank(message="Bisogna inserire l' email")
	@Email(message="Formato errato")
	@Pattern(
	        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
	        message = "Il dominio dell' email non è valido."
	    )
private String email;
	@NotBlank(message="Inserire il soggetto")
private String subject;
	@NotBlank(message="Inserire il messaggio")
private String message;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}




}
