package fr.sodifrance.hackatoncaf.model;

public class CommuneDetail extends Commune {

	private String insee;
	private String name;
	private Integer nbAllocs;

	/**
	 * @return the code_insee
	 */
	public String getInsee() {
		return insee;
	}

	/**
	 * @param code_insee
	 *            the code_insee to set
	 */
	public void setInsee(String insee) {
		this.insee = insee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nbAllocataires
	 */
	public Integer getNbAllocs() {
		return nbAllocs;
	}

	/**
	 * @param nbAllocs
	 *            the nbAllocataires to set
	 */
	public void setNbAllocs(Integer nbAllocs) {
		this.nbAllocs = nbAllocs;
	}

}
