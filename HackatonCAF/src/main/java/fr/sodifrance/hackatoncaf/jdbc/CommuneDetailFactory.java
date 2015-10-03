package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.CommuneDetail;

public class CommuneDetailFactory extends AbstractCommuneFactory<CommuneDetail> {

	@Override
	protected CommuneDetail create(String insee ,
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
					 
		CommuneDetail commune = new CommuneDetail();
		commune.setCode(insee);
		commune.setInsee(insee);
		commune.setName(name);
		
		commune.setRatio2aFreqCreche(ratio2aFreqCreche);
		commune.setRatio4aPharm(ratio4aPharm);
		commune.setRatio5aMatern(ratio5aMatern);
		commune.setRatio6sage(ratio6sage);
		commune.setRatio7elem(ratio7elem);
		
		commune.setNbEnfant3(nbEnfant3);
		commune.setNbEnfant36(nbEnfant36);
		commune.setNbEnfant6(nbEnfant6);
		commune.setNbCrechePlaceDispo(nbCrechePlaceDispo);
		commune.setNbCreche(nbCreche);	
		commune.setNbPharmacie(nbPharma);
		commune.setNbSage(nbSage);
		commune.setNbMaternelle(nbMaternelle);
		commune.setNbElem(nbElem);
		commune.setNbPop(nbPop);
		return commune;
	}

}
