package br.com.cjm.sismc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cjm.sismc.dto.EmailDTO;
import br.com.cjm.sismc.security.JWTUtil;
import br.com.cjm.sismc.security.UserSS;
import br.com.cjm.sismc.services.AuthService;
import br.com.cjm.sismc.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value="/refresh_token" ,method=RequestMethod.POST)
	public ResponseEntity<?> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generationToken(user.getUsername());
		response.addHeader("Authorization", "Beare "+token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/forgot" ,method=RequestMethod.POST)
	public ResponseEntity<?> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}

}
