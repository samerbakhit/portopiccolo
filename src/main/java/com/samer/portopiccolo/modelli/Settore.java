package com.samer.portopiccolo.modelli;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="settore")
public class Settore {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long idSettore;
	@NotBlank(message="Bisogna inserire il settore")
private String settore;
	public Long getIdSettore() {
		return idSettore;
	}
	public void setIdSettore(Long idSettore) {
		this.idSettore = idSettore;
	}
	public String getSettore() {
		return settore;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}


}
