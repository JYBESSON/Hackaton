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

		// foyers allocataires / nombre dâ€™habitants de la commune
		// Communes;Codes_Insee;NB_Allocataires;ALL_PAJE;ALL_PRIM;ALL_BASEP;ALL_CMG;ALL_CMG_ASMA;ALL_CMG_DOM;ALL_CMG_A;ALL_Clca_Colca
		jdbcTemplate.execute("DROP TABLE IF EXISTS PAJE");
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS PAJE (CODE_INSEE VARCHAR(20), NB_ALLOCATAIRES VARCHAR(20), ANNEE NUMBER)"
						+ " AS (" + " SELECT Codes_Insee, NB_Allocataires, 2013"
						+ " FROM CSVREAD('classpath:db/PAJECom2013.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')"
						+ " UNION " + " SELECT Codes_Insee, NB_Allocataires, 2014"
						+ " FROM CSVREAD('classpath:db/PAJECom2014.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')"
						+ ")");
		jdbcTemplate.execute("CREATE INDEX ON PAJE(CODE_INSEE) ");

		// Geolocalisation des communues
		// EU_circo
		// code_rÃƒÂ©gion;nom;_rÃƒÂ©gionchef-lieu_rÃƒÂ©gion;numÃƒÂ©ro_dÃƒÂ©partement;nom_dÃƒÂ©partement;prÃƒÂ©fecture;numÃƒÂ©ro_circonscription;nom_commune;codes_postaux;code_insee;latitude;longitude;ÃƒÂ©loignement
		// voir
		// https://www.data.gouv.fr/fr/datasets/listes-des-communes-geolocalisees-par-regions-departements-circonscriptions-nd/
		jdbcTemplate.execute("DROP TABLE IF EXISTS COMMUNE ");
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS COMMUNE (CODE_INSEE VARCHAR(20), NOM TEXT, LATITUDE VARCHAR, LONGITUDE VARCHAR)"
						+ " AS SELECT code_insee, nom_commune, latitude, longitude"
						+ " FROM CSVREAD('classpath:db/eucircos_regions_departements_circonscriptions_communes_gps.csv', NULL, 'charset=UTF-8 fieldSeparator=; writeColumnHeader=false')");
		jdbcTemplate.execute("CREATE INDEX ON COMMUNE(CODE_INSEE) ");

		// Bases de donnÃ©es des communes de l'INSEE avec le nombre d'habitants
		jdbcTemplate.execute("DROP TABLE IF EXISTS INSEE ");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS INSEE " + " AS SELECT *"
				+ " FROM CSVREAD('classpath:db/base-ic-evol-struct-pop-2011.csv')");
	}

}
