package fr.sodifrance.hackatoncaf.model;

/**
 * POJO de base de l'application
 * @author JY
 *
 */
public class Commune {
	
	private String codeInsee;
	
	private Integer nbAllocataires;
	
	private Integer score;

	private Loc loc;
	
	/**
	 * @return the code_insee
	 */
	public String getCodeInsee() {
		return codeInsee;
	}

	/**
	 * @param code_insee the code_insee to set
	 */
	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	/**
	 * @return the nbAllocataires
	 */
	public Integer getNbAllocataires() {
		return nbAllocataires;
	}

	/**
	 * @param nbAllocataires the nbAllocataires to set
	 */
	public void setNbAllocataires(Integer nbAllocataires) {
		this.nbAllocataires = nbAllocataires;
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
