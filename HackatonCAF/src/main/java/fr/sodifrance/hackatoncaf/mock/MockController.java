package fr.sodifrance.hackatoncaf.mock;

import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

	@Autowired private ServletContext servletContext;

	@SuppressWarnings("resource")
	@RequestMapping(value = "/mock", produces = MediaType.APPLICATION_JSON_VALUE)
	public String mock() {
		InputStream in = servletContext.getResourceAsStream("/WEB-INF/mock.json");
		return new Scanner(in, "UTF-8").useDelimiter("\\A").next();
	}

}
