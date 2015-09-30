package fr.sodifrance.hackatoncaf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "fr.sodifrance")
public class HackatonApplication implements CommandLineRunner {

	public static void main(String[] args) throws Exception {

		SpringApplication sa = new SpringApplication(HackatonApplication.class);
		sa.run(args);

	}

	public void run(String... strings) throws Exception {
		
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
		Statement stat = conn.createStatement();

		// foyers allocataires / nombre d’habitants de la commune
		// Communes;Codes_Insee;NB_Allocataires;ALL_PAJE;ALL_PRIM;ALL_BASEP;ALL_CMG;ALL_CMG_ASMA;ALL_CMG_DOM;ALL_CMG_A;ALL_Clca_Colca
		stat.execute("DROP TABLE IF EXISTS PAJE");
		stat.execute("CREATE TABLE IF NOT EXISTS PAJE (CODE_INSEE VARCHAR(20), NB_ALLOCATAIRES VARCHAR(20), ANNEE NUMBER)"
		        + " AS ("
		        + " SELECT Codes_Insee, NB_Allocataires, 2013"
		        + " FROM CSVREAD('classpath:db/PAJECom2013.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')"
		        + " UNION "
		        + " SELECT Codes_Insee, NB_Allocataires, 2014"
		        + " FROM CSVREAD('classpath:db/PAJECom2014.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')"		        
		        + ")");
		stat.execute("CREATE INDEX ON PAJE(CODE_INSEE) ");
		
		// Geolocalisation des communues
		// EU_circo	code_rÃ©gion;nom;_rÃ©gionchef-lieu_rÃ©gion;numÃ©ro_dÃ©partement;nom_dÃ©partement;prÃ©fecture;numÃ©ro_circonscription;nom_commune;codes_postaux;code_insee;latitude;longitude;Ã©loignement
		// voir https://www.data.gouv.fr/fr/datasets/listes-des-communes-geolocalisees-par-regions-departements-circonscriptions-nd/
		stat.execute("DROP TABLE IF EXISTS COMMUNE ");
		stat.execute("CREATE TABLE IF NOT EXISTS COMMUNE (CODE_INSEE VARCHAR(20), NOM TEXT, LATITUDE VARCHAR, LONGITUDE VARCHAR)"
		        + " AS SELECT code_insee, nom_commune, latitude, longitude"
		        + " FROM CSVREAD('classpath:db/eucircos_regions_departements_circonscriptions_communes_gps.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')");
		stat.execute("CREATE INDEX ON COMMUNE(CODE_INSEE) ");
		
		// Bases de données des communes de l'INSEE avec le nombre d'habitants
		stat.execute("DROP TABLE IF EXISTS INSEE ");
		stat.execute("CREATE TABLE IF NOT EXISTS INSEE "
		        + " AS SELECT *"
		        + " FROM CSVREAD('classpath:db/base-ic-evol-struct-pop-2011.csv')");
		
		stat.close();
		conn.close();
	}

}
