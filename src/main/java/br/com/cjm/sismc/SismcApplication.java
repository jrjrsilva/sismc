package br.com.cjm.sismc;




import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SismcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SismcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}

