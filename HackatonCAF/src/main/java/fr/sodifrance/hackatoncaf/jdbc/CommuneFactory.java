package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.Commune;

public class CommuneFactory extends AbstractCommuneFactory<Commune> {

	@Override
	protected Commune create(String insee,
			 String name,	
			 Double ratio2aFreqCreche,
			 Double ratio4aPharm,
			 Integer nbEnfant3,
			 Integer nbEnfant36,
			 Integer nbCrechePlaceDispo,
			 Integer nbCreche,
			 Integer nbPharmacie,
			 Integer nbSage,
			 Integer nbMaternelle,
			 Integer nbElem,
			 Integer nbPop,
			 String geometry) {
		Commune commune = new Commune();
		commune.setCode(insee);
		commune.setGeometry(geometry);
		return commune;
		
	}
}
