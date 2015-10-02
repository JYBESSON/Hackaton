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
		//NB_ECOLE_ELEM;POP
		// Chargement du fichier csv	
		jdbcTemplate.execute("DROP TABLE IF EXISTS HakDB");
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS HakDB ("		
							 + " Codes_Insee VARCHAR(20) , "
							 + " Communes VARCHAR(60) , "
							 + " ALL_PAJE_2009 VARCHAR(20) , "
							 + " ALL_PAJE_2010 VARCHAR(20) , " 
							 + " ALL_PAJE_2011 VARCHAR(20) , "
							 + " ALL_PAJE_2012 VARCHAR(20) , "
							 + " ALL_PAJE_2013 VARCHAR(20) , "
							 + " ALL_PAJE_2014 VARCHAR(20) , "
							 + " NB_SAGE VARCHAR(20) , "
							 + " NB_PHARMA VARCHAR(20) , " 
							 + " NB_MATERNELLE VARCHAR(20) , " 
							 + " NB_ECOLE_ELEM VARCHAR(20) , "
							 + " POP VARCHAR(20) , "
							 + " LATITUDE VARCHAR(20) , "
							 + " LONGITUDE VARCHAR(20) "
							 + ")"
		 + " AS (" + " SELECT Codes_Insee, " 
						  + " Communes,"
						  + " ALL_PAJE_2009,"
						  + " ALL_PAJE_2010,"
						  + " ALL_PAJE_2011,"
						  + " ALL_PAJE_2012,"
						  + " ALL_PAJE_2013,"
						  + " ALL_PAJE_2014,"
						  + " NB_SAGE,"
						  + " NB_PHARMA,"
						  + " NB_MATERNELLE,"
						  + " NB_ECOLE_ELEM, "
						  + " POP , "
						  + " LATITUDE,"
						  + " LONGITUDE "
		+ " FROM CSVREAD('classpath:db/HakDb.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')"
			+ ")");
	}

}
