package fr.sodifrance.hackatoncaf.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import fr.sodifrance.hackatoncaf.model.Commune;
import fr.sodifrance.hackatoncaf.model.Loc;

public class MyRowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

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
					
			Commune data = new Commune();

			// Ici on teste si la commune n'est pas nulle pour l'ajouter a
			// la
			// liste.
			if (data != null) {
								
				data.setCode(rs.getString(1));		
				//data.setScore(rs.getInt(2));
				data.setLoc(new Loc(rs.getDouble(3), rs.getDouble(4)));
				
				
				//Commune com = (Commune) data;
				//com.setGeometry(rs.getString(5));
				results.add((T)data);
			}
		}
		return results;
	}

}