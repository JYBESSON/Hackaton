package fr.sodifrance.hackatoncaf.model;

/**
 * POJO de base de l'application
 * @author JY
 *
 */
public class Commune {

	
	private String code_insee;
	
	private String name;
	
	private Integer nb_allocataires;

	
	
	/**
	 * @return the code_insee
	 */
	public String getCode_insee() {
		return code_insee;
	}

	/**
	 * @param code_insee the code_insee to set
	 */
	public void setCode_insee(String code_insee) {
		this.code_insee = code_insee;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nb_allocataires
	 */
	public Integer getNb_allocataires() {
		return nb_allocataires;
	}

	/**
	 * @param nb_allocataires the nb_allocataires to set
	 */
	public void setNb_allocataires(Integer nb_allocataires) {
		this.nb_allocataires = nb_allocataires;
	}
	
	
	
}
