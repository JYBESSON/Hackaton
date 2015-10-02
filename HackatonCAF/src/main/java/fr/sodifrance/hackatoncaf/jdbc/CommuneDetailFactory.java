package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.CommuneDetail;

public class CommuneDetailFactory extends AbstractCommuneFactory<CommuneDetail> {

	@Override
	protected CommuneDetail create(String insee, String name, Integer nbAllocs, Integer nbPharmacie, Integer nbSage, Integer nbMaternelle, Integer nbElem, Integer nbPop) {
		
		CommuneDetail commune = new CommuneDetail();
		commune.setInsee(insee);
		commune.setName(name);
		commune.setNbAllocs(nbAllocs);
		commune.setNbPharmacie(nbPharmacie);
		commune.setNbSage(nbSage);
		commune.setNbMaternelle(nbMaternelle);
		commune.setNbElem(nbElem);
		commune.setNbPop(nbPop);
			
		return commune;
	}

}
