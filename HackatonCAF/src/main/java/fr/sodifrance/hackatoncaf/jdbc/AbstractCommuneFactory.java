package fr.sodifrance.hackatoncaf.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import fr.sodifrance.hackatoncaf.HackatonRestController;
import fr.sodifrance.hackatoncaf.model.Commune;
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
		Integer nbAllocataires = getInteger(rs, 5);
		Integer nbSage = getInteger(rs, 6);
		Integer nbPharma = getInteger(rs, 7);
		Integer nbMaternelle = getInteger(rs, 8);

		Integer score = HackatonRestController.computeScore(nbAllocataires);
		if (latitude != null && latitude != 0 && longitude != null && longitude != 0 && score != null) {
			T commune = create(insee, name, nbAllocataires, nbPharma, nbSage, nbMaternelle);
			commune.setLoc(new Loc(latitude, longitude));
			commune.setScore(score);
			return commune;
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

	protected abstract T create(String insee, String name, Integer nbAllocs, Integer nbPharmacie, Integer nbSage,
			Integer nbMaternelle);

}
