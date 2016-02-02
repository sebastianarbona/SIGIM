package com.core.irrigacion.domain.ubicacion;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString(excludeFields={"id", "descripcion"})
@RooJpaActiveRecord
@RooJson
public class Localidad {
	private String nombre;
	private int codigoP;
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	@ManyToOne
	private Departamento departamento;
	
}
