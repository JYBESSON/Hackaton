package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.Commune;

public class CommuneFactory extends AbstractCommuneFactory<Commune> {

	@Override
	protected Commune create(String insee ,
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
			Integer nbPop ) {
		Commune commune = new Commune();
		commune.setCode(insee);
	
		return commune;
		
	}
}
