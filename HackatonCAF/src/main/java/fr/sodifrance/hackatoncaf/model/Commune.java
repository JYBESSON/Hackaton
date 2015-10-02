package fr.sodifrance.hackatoncaf.model;

/**
 * POJO de base de l'application
 * @author JY
 *
 */
public class Commune {
	
	private String code;
	
	private Integer score;

	private Loc loc;
	
	private String geometry;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	
}
