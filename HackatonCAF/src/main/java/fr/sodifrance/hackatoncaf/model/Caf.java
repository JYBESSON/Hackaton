package fr.sodifrance.hackatoncaf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Caf {

	  	@Id
	  	@GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	  	
	    private String dep;
	    
	    private String deplib;
	    
	    private String reg;
	    
	    
	    private String nombre;

	    protected Caf() {}

	    public Caf(String dep, String deplib, String reg, String nombre) {
			super();
			this.dep = dep;
			this.deplib = deplib;
			this.reg = reg;
			this.nombre = nombre;
		}

	
}
