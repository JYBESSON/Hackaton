package fr.sodifrance.hackatoncaf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.sodifrance.hackatoncaf.jdbc.CommuneDetailFactory;
import fr.sodifrance.hackatoncaf.jdbc.CommuneFactory;
import fr.sodifrance.hackatoncaf.jdbc.MyRowMapperResultSetExtractor;
import fr.sodifrance.hackatoncaf.model.Commune;
import fr.sodifrance.hackatoncaf.model.CommuneDetail;

@RestController
public class HackatonRestController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * Retourne la liste des communes avec leur score + géolocalisation.
	 * 
	 * @param annee
	 * @param from
	 * @param to
	 * @return
	 */
	@RequestMapping(value = "/communes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Commune> communes(
			@RequestParam(value = "from", required = false) Integer from,
			@RequestParam(value = "to", required = false) Integer to) {

		CommuneFactory mapper = new CommuneFactory();
		
		List<Commune> communes = jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				 											  				
				String lQuery = "SELECT Codes_Insee, "
									    + "SCORE, "
										+ "LATITUDE, "
										+ "LONGITUDE "											
										+ " FROM HakDb ";										
				
				PreparedStatement ps = connection
						.prepareStatement(lQuery);
												
			
				return ps;
			}
		}, new MyRowMapperResultSetExtractor<Commune>(mapper));

		// Reparcours des communes pour mettre a jour le score + supprimer les
		// communes qui n'ont pas un score compris entre from et to.
		Integer maxScore = mapper.getMaxScore();
		if (maxScore == null) {
			return communes;
		}
		float max = (float) maxScore;
		List<Commune> filteredCommunes = new ArrayList<Commune>();
		Integer score = null;
		for (Commune commune : communes) {
			// Mise a jour du score de la commune par rapport au score max.
			score = commune.getScore();
			score = (int) (((float) score) / max * 100);
			commune.setScore(score);
			// test si le score de la commune est compris entre from et to
			if (isInRange(score, from, to)) {
				filteredCommunes.add(commune);
			}
		}
		return filteredCommunes;
	}

	private boolean isInRange(Integer score, Integer from, Integer to) {
		if (score == null) {
			return false;
		}
		if (from != null && score < from) {
			return false;
		}
		if (to != null && score > to) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/commune", produces = MediaType.APPLICATION_JSON_VALUE)
	public CommuneDetail commune(@RequestParam(value = "code", required = true) final String code) {
		CommuneDetail commune = jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				
				String lQuery = "SELECT Codes_Insee, " 	
								 + " Communes  , "
								 + " LATITUDE, "
								 + " LONGITUDE, "
								 + " RATIO_2A_FREQ_CRECHE  , "
								 + " RATIO_4A_PHARM  , "
								 + " RATIO_5A_MATERN,"
								 + " RATIO_6_SAGE,"
								 + " RATIO_7_ELEM_POP610,"
								 + " SCORE,"
								 + " ENF_MOINS_3  , "
								 + " ENF_3_6   , "
								 + " ENF_6_10 , "
								 + " CRECHE_PL_DISPO   , "
								 + " NB_CRECHE   , "
								 + " NB_SAGE , "
								 + " NB_PHARMA  , " 
								 + " NB_MATERNELLE  , " 
								 + " NB_ECOLE_ELEM  , "
								 + " POP  , "
								 + " LATITUDE  , "
								 + " LONGITUDE  "								 
						+ " FROM HakDb " 
						+ "WHERE Codes_Insee = ?";
				
				PreparedStatement ps = connection
						.prepareStatement(lQuery);
						
				ps.setString(1, code);				
						
				return ps;
			}
		}, (ResultSetExtractor<CommuneDetail>)(new CommuneDetailFactory()));
		return commune;
	}

	/**
	 * Calcul du score d'une commune en fonction du son nombre allocataires, sa
	 * population, etc
	 * 
	 * @param nbAllocataires
	 * @return
	 */
	public static Integer computeScore(Integer nbAllocataires) {
		// TODO: effectuer ce calcul.
		if (nbAllocataires != null) {
			return nbAllocataires;
		}
		return -1;
	}

}
