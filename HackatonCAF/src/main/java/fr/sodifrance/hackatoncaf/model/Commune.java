package fr.sodifrance.hackatoncaf.model;

/**
 * POJO de base de l'application
 * @author JY
 *
 */
public class Commune {
	
	private Integer score;

	private Loc loc;
	
		
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
