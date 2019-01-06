package br.com.cjm.sismc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cjm.sismc.security.JWTUtil;
import br.com.cjm.sismc.security.UserSS;
import br.com.cjm.sismc.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="/refresh_token" ,method=RequestMethod.POST)
	public ResponseEntity<?> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generationToken(user.getUsername());
		response.addHeader("Authorization", "Beare "+token);
		return ResponseEntity.noContent().build();
	}

}
