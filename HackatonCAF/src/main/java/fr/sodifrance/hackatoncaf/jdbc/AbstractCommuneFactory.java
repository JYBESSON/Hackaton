package fr.sodifrance.hackatoncaf.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import fr.sodifrance.hackatoncaf.HackatonRestController;
import fr.sodifrance.hackatoncaf.model.Commune;
import fr.sodifrance.hackatoncaf.model.CommuneDetail;
import fr.sodifrance.hackatoncaf.model.Loc;

public abstract class AbstractCommuneFactory<T extends Commune> implements RowMapper<T>, ResultSetExtractor<T> {

	private Integer maxScore;

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T commune = extractData(rs);
		if (commune != null) {
			Integer score = commune.getScore();
			if (maxScore == null || maxScore < score) {
				maxScore = score;
			}
		}
		return commune;
	}

	@Override
	public T extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (!rs.next()) {
			return null;
		}
		
		
		
		String insee = rs.getString(1);
		String name = rs.getString(2);
		Double latitude = getDouble(rs, 3);
		Double longitude = getDouble(rs, 4);
		Double ratio2aFreqCreche = getDouble(rs, 5);
		Double ratio4aPharm = getDouble(rs, 6);		
		Double ratio5aMatern = getDouble(rs, 7);
		Double ratio6sage = getDouble(rs, 8);
		Double ratio7elem = getDouble(rs, 9);	
		Integer score =getInteger(rs, 10);
		Integer nbEnfant3 = getInteger(rs,11);
		Integer nbEnfant36 = getInteger(rs, 12);
		Integer nbEnfant6 = getInteger(rs, 13);		
		Integer nbCrechePlaceDispo = getInteger(rs, 14);
		Integer nbCreche = getInteger(rs, 15);
		Integer nbSage = getInteger(rs, 16);
		Integer nbPharma = getInteger(rs, 17);
		Integer nbMaternelle = getInteger(rs, 18);
		Integer nbElem = getInteger(rs, 19);
		Integer nbPop = getInteger(rs, 20);
	
		
		
		if (latitude != null && latitude != 0 && longitude != null && longitude != 0 && score != null) {
			
			CommuneDetail commune = new CommuneDetail();
			
		
			commune.setCode(insee);
			commune.setInsee(insee);
			commune.setName(name);
			
			commune.setRatio2aFreqCreche(ratio2aFreqCreche);
			commune.setRatio4aPharm(ratio4aPharm);
			commune.setRatio5aMatern(ratio5aMatern);
			commune.setRatio6sage(ratio6sage);
			commune.setRatio7elem(ratio7elem);
			
			commune.setNbEnfant3(nbEnfant3);
			commune.setNbEnfant36(nbEnfant36);
			commune.setNbEnfant6(nbEnfant6);
			commune.setNbCrechePlaceDispo(nbCrechePlaceDispo);
			commune.setNbCreche(nbCreche);	
			commune.setNbPharmacie(nbPharma);
			commune.setNbSage(nbSage);
			commune.setNbMaternelle(nbMaternelle);
			commune.setNbElem(nbElem);
			commune.setNbPop(nbPop);
				
			commune.setLoc(new Loc(latitude, longitude));
			commune.setScore(score);
			
			return (T)commune;
		}
		// la commune n'a pas de geolocalisation, on retourne null
		return null;
	}

	private Double getDouble(ResultSet rs, int i) {
		try {
			return rs.getDouble(i);
		} catch (SQLException e) {
			return null;
		}
	}

	private Integer getInteger(ResultSet rs, int i) {
		try {
			return rs.getInt(i);
		} catch (SQLException e) {
			return null;
		}
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	protected abstract T create(String insee ,
			String name ,
			Double latitude, 
			Double longitude,
			Double ratio2aFreqCreche,
			Double ratio4aPharm	,
			Double ratio5aMatern ,
			Double ratio6sage ,
			Double ratio7elem,
			Integer score ,
			Integer nbEnfant3 ,
			Integer nbEnfant36 ,
			Integer nbEnfant6 ,
			Integer nbCrechePlaceDispo,
			Integer nbCreche ,
			Integer nbSage ,
			Integer nbPharma,
			Integer nbMaternelle,
			Integer nbElem,
			Integer nbPop );

}
