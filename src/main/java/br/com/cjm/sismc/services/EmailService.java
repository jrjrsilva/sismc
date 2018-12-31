package br.com.cjm.sismc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.cjm.sismc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
