package fr.sodifrance.hackatoncaf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.sodifrance.hackatoncaf.model.Commune;
import fr.sodifrance.hackatoncaf.model.Loc;

@RestController
public class HackatonRestController {

	@RequestMapping(value = "/communes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Commune> test(@RequestParam(value = "annee", required = false) Integer annee) {

		if (annee == null) {
			annee = 2014;
		}

		List<Commune> communes = new ArrayList<Commune>();
		Commune commune;
		ResultSet res = null;
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");

			Statement stat = conn.createStatement();
			res = stat.executeQuery(
					"SELECT C.CODE_INSEE, C.LATITUDE, C.LONGITUDE, P.NB_ALLOCATAIRES FROM COMMUNE AS C INNER JOIN PAJE AS P ON (C.CODE_INSEE = P.CODE_INSEE AND P.ANNEE = "
							+ annee + ") ");

			String codeInsee = null;
			Integer nbAllocataires = null;
			Double latitude = null;
			Double longitude = null;
			while (res.next()) {

				codeInsee = res.getString(1);
				latitude = getDouble(res.getString(2));
				longitude = getDouble(res.getString(3));
				nbAllocataires = getInteger(res.getString(4));

				if (latitude != null && longitude != null) {
					commune = new Commune();
					commune.setCodeInsee(codeInsee);
					commune.setLoc(new Loc(latitude, longitude));
					commune.setNbAllocataires(nbAllocataires);
					commune.setScore(computeScore(nbAllocataires));
					communes.add(commune);
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

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

	private Integer getInteger(String s) {
		if (s == null) {
			return null;
		}
		try {
			return Integer.parseInt(s);
		} catch (Throwable e) {
			return null;
		}
	}

	private Double getDouble(String s) {
		if (s == null) {
			return null;
		}
		try {
			return Double.parseDouble(s);
		} catch (Throwable e) {
			return null;
		}
	}
}
