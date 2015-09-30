package fr.sodifrance.hackatoncaf.model;

/**
 * POJO de base de l'application
 * @author JY
 *
 */
public class Commune {
	
	private String insee;
	
	private Integer nbAllocs;
	
	private Integer score;

	private Loc loc;
	
	/**
	 * @return the code_insee
	 */
	public String getInsee() {
		return insee;
	}

	/**
	 * @param code_insee the code_insee to set
	 */
	public void setInsee(String insee) {
		this.insee = insee;
	}

	/**
	 * @return the nbAllocataires
	 */
	public Integer getNbAllocs() {
		return nbAllocs;
	}

	/**
	 * @param nbAllocs the nbAllocataires to set
	 */
	public void setNbAllocs(Integer nbAllocs) {
		this.nbAllocs = nbAllocs;
	}
	
	public void setLoc(Loc loc) {
		this.loc = loc;
	}
	
	public Loc getLoc() {
		return loc;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Integer getScore() {
		return score;
	}
	
}
