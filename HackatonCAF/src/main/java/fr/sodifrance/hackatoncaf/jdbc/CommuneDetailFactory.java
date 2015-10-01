package fr.sodifrance.hackatoncaf.jdbc;

import fr.sodifrance.hackatoncaf.model.CommuneDetail;

public class CommuneDetailFactory extends AbstractCommuneFactory<CommuneDetail> {

	@Override
	protected CommuneDetail create(String insee, String name, Integer nbAllocs) {
		CommuneDetail commune = new CommuneDetail();
		commune.setInsee(insee);
		commune.setName(name);
		commune.setNbAllocs(nbAllocs);
		return commune;
	}

}
