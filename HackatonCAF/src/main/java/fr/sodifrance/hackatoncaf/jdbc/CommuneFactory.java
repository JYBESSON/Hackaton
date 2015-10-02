package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.Commune;

public class CommuneFactory extends AbstractCommuneFactory<Commune> {

	@Override
	protected Commune create(String insee, String name, Integer nbAllocs, Integer nbPharmacie, Integer nbSage, Integer nbMaternelle, Integer nbElem, Integer nbPop) {
		Commune commune = new Commune();
		commune.setCode(insee);
		return commune;
		
	}
}
