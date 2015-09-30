package fr.sodifrance.hackatoncaf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.sodifrance.hackatoncaf.model.Commune;
import fr.sodifrance.hackatoncaf.model.Loc;

@RestController
public class HackatonRestController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/communes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Commune> test(@RequestParam(value = "annee", required = false) Integer annee) {
		final int anneeFilter = annee != null ? annee : 2014;
		List<Commune> communes = jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement("SELECT C.CODE_INSEE, C.LATITUDE, C.LONGITUDE, P.NB_ALLOCATAIRES "
								+ "FROM COMMUNE AS C INNER JOIN PAJE AS P "
								+ "ON (C.CODE_INSEE = P.CODE_INSEE AND P.ANNEE = ?)", new String[] { "annee" });
				ps.setInt(1, anneeFilter);
				return ps;
			}
		}, new MyRowMapperResultSetExtractor<Commune>(new RowMapper<Commune>() {
			@Override
			public Commune mapRow(ResultSet rs, int rowNum) throws SQLException {
				String codeInsee = rs.getString(1);
				Double latitude = getDouble(rs, 2);
				Double longitude = getDouble(rs, 3);
				Integer nbAllocataires = getInteger(rs, 4);
				if (latitude != null && longitude != null) {
					Commune commune = new Commune();
					commune.setInsee(codeInsee);
					commune.setLoc(new Loc(latitude, longitude));
					commune.setNbAllocs(nbAllocataires);
					commune.setScore(computeScore(nbAllocataires));
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

		}));

		return communes;

	}

	/**
	 * Calcul du score d'uen commune en fonction du son nombre allocataires, sa
	 * population, etc
	 * 
	 * @param nbAllocataires
	 * @return
	 */
	private Integer computeScore(Integer nbAllocataires) {
		// TODO: effectuer ce calcul.
		if (nbAllocataires != null) {
			return nbAllocataires;
		}
		return -1;
	}

	private class MyRowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

		private final RowMapper<T> rowMapper;

		private final int rowsExpected;

		/**
		 * Create a new RowMapperResultSetExtractor.
		 * 
		 * @param rowMapper
		 *            the RowMapper which creates an object for each row
		 */
		public MyRowMapperResultSetExtractor(RowMapper<T> rowMapper) {
			this(rowMapper, 0);
		}

		/**
		 * Create a new RowMapperResultSetExtractor.
		 * 
		 * @param rowMapper
		 *            the RowMapper which creates an object for each row
		 * @param rowsExpected
		 *            the number of expected rows (just used for optimized
		 *            collection handling)
		 */
		public MyRowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected) {
			Assert.notNull(rowMapper, "RowMapper is required");
			this.rowMapper = rowMapper;
			this.rowsExpected = rowsExpected;
		}

		@Override
		public List<T> extractData(ResultSet rs) throws SQLException {
			List<T> results = (this.rowsExpected > 0 ? new ArrayList<T>(this.rowsExpected) : new ArrayList<T>());
			int rowNum = 0;
			while (rs.next()) {
				T data = this.rowMapper.mapRow(rs, rowNum++);
				// Ici on teste si la commune n'est pas nulle pour l'ajouter a
				// la
				// liste.
				if (data != null) {
					results.add(data);
				}
			}
			return results;
		}

	}

}
