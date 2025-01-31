package com.samer.portopiccolo.DipendentiRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samer.portopiccolo.modelli.EmailRequest;

public interface EmailRepository extends JpaRepository<EmailRequest,Long> {

	
}
