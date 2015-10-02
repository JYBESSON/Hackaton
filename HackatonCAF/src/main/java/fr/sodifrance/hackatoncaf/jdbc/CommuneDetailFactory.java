package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.CommuneDetail;

public class CommuneDetailFactory extends AbstractCommuneFactory<CommuneDetail> {

	@Override
	protected CommuneDetail create(String insee,
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
					 
		CommuneDetail commune = new CommuneDetail();
		commune.setCode(insee);
		commune.setInsee(insee);
		commune.setName(name);
		commune.setNbEnfant3(nbEnfant3);
		commune.setNbEnfant36(nbEnfant36);
		commune.setNbCrechePlaceDispo(nbCrechePlaceDispo);
		commune.setNbCreche(nbCreche);	
		commune.setNbPharmacie(nbPharmacie);
		commune.setNbSage(nbSage);
		commune.setNbMaternelle(nbMaternelle);
		commune.setNbElem(nbElem);
		commune.setNbPop(nbPop);
		commune.setGeometry(geometry);
			
		return commune;
	}

}
