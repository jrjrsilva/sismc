package br.com.cjm.sismc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cjm.sismc.services.DBService;
import br.com.cjm.sismc.services.EmailService;
import br.com.cjm.sismc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbService;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dbService.instatiateTestDatabase();
		return true;
	}
	 
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
