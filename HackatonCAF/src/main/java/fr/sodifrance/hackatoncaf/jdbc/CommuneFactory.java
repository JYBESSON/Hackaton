package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.Commune;

public class CommuneFactory extends AbstractCommuneFactory<Commune> {

	@Override
	protected Commune create(String insee, String name, Integer nbAllocs) {
		return new Commune();
	}
}
