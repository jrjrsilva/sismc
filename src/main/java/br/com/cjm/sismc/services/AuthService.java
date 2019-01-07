package br.com.cjm.sismc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cjm.sismc.domain.Cliente;
import br.com.cjm.sismc.repositories.ClienteRepository;
import br.com.cjm.sismc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	private Random rand = new Random();
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * Recebe o emal verifica se existe e gera uma nova senha que é enviada por email
	 * @param email
	 */
	public void sendNewPassword(String email){
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente, newPass);
	}
	
	//Gerar uma nova senha
	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i< 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	//Seleciona os caracteres da senha de acordo os itens informados abaixo
	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {// gera um dígito
			return (char) (rand.nextInt(10)+ 48);
		}else if(opt == 1) {// gera letra maiuscula
			return (char) (rand.nextInt(26)+ 65);
		}else {// gera letra miniscula
			return (char) (rand.nextInt(26)+ 97);
		}
	}
	
}
