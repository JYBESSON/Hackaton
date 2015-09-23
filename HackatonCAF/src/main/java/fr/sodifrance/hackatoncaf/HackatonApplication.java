package fr.sodifrance.hackatoncaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import fr.sodifrance.hackatoncaf.model.Caf;
import fr.sodifrance.hackatoncaf.repository.CafRepository;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "fr.sodifrance")
public class HackatonApplication implements CommandLineRunner {

	@Autowired
	CafRepository repository;

	public static void main(String[] args) throws Exception {

		SpringApplication sa = new SpringApplication(HackatonApplication.class);
		sa.run(args);

	}

	public void run(String... strings) throws Exception {

		repository.save(new Caf("1", "Ain", "82", "15853"));
		repository.save(new Caf("2", "Aisne", "22", "15749"));
		repository.save(new Caf("3", "Allier", "83", "7808"));
		repository.save(new Caf("4", "Alpes-de-Haute-Provence", "93", "3702"));
		repository.save(new Caf("5", "Hautes-Alpes", "93", "3407"));
		repository.save(new Caf("6", "Alpes-Maritimes", "93", "25377"));
		repository.save(new Caf("7", "Ardèche", "82", "7916"));
		repository.save(new Caf("8", "Ardennes", "21", "7494"));

	}

}
