package fr.sodifrance.hackatoncaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "fr.sodifrance" )
public class HackatonApplication {
			
	public static void main(String[] args) throws Exception {
		
		SpringApplication sa = new SpringApplication(HackatonApplication.class);
		sa.run(args);
		
	}
	
	
}
