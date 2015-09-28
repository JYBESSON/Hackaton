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
import org.springframework.web.bind.annotation.RestController;

import fr.sodifrance.hackatoncaf.model.Commune;

@RestController
public class HackatonRestController {

	@RequestMapping(value = "/communes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Commune> test() {

		List<Commune> communes = new ArrayList<Commune>();
		Commune commune;

		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
			Statement stat = conn.createStatement();

			ResultSet res = stat.executeQuery("SELECT * FROM PAJE");

			while (res.next()) {
				commune = new Commune();

				String[] test = res.getString(1).split(";");
				
				commune.setName(test[0]);
				if (test.length > 1) {
					commune.setCode_insee(test[1]);
				}
				if (test.length > 2) {
					commune.setNb_allocataires(getInteger(test[2]));
				}				

				communes.add(commune);
			}

			res.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return communes;

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

}
