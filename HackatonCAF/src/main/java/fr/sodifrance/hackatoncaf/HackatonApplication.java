package fr.sodifrance.hackatoncaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "fr.sodifrance")
public class HackatonApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws Exception {

		SpringApplication sa = new SpringApplication(HackatonApplication.class);
		sa.run(args);
	}

	public void run(String... strings) throws Exception {
					
		// Chargement du fichier csv	
		jdbcTemplate.execute("DROP TABLE IF EXISTS HakDB");
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS HakDB ("		
							 + " Codes_Insee VARCHAR(20) , "
							 + " Communes VARCHAR(60) , "
							 + " RATIO_2A_FREQ_CRECHE VARCHAR(60) , "
							 + " RATIO_4A_PHARM VARCHAR(60) , "
							 + " ENF_MOINS_3 VARCHAR(60) , "
							 + " ENF_3_6  VARCHAR(60) , "
							 + " CRECHE_PL_DISPO  VARCHAR(60) , "
							 + " NB_CRECHE  VARCHAR(60) , "
							 + " NB_SAGE VARCHAR(20) , "
							 + " NB_PHARMA VARCHAR(20) , " 
							 + " NB_MATERNELLE VARCHAR(20) , " 
							 + " NB_ECOLE_ELEM VARCHAR(20) , "
							 + " POP VARCHAR(20) , "
							 + " LATITUDE VARCHAR(20) , "
							 + " LONGITUDE VARCHAR(20), "
							 + " GEOMETRY CLOB(100000) "
							 + ")"
		 + " AS (" + " SELECT Codes_Insee, " 	
						 + " Communes  , "
						 + " RATIO_2A_FREQ_CRECHE  , "
						 + " RATIO_4A_PHARM  , "
						 + " ENF_MOINS_3  , "
						 + " ENF_3_6   , "
						 + " CRECHE_PL_DISPO   , "
						 + " NB_CRECHE   , "
						 + " NB_SAGE , "
						 + " NB_PHARMA  , " 
						 + " NB_MATERNELLE  , " 
						 + " NB_ECOLE_ELEM  , "
						 + " POP  , "
						 + " LATITUDE  , "
						 + " LONGITUDE , "
						 + " GEOMETRY  "
		+ " FROM CSVREAD('classpath:db/HackDb.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')"
			+ ")");
	}

}
