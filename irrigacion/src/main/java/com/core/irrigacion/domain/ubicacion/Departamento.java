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
@RooToString
@RooJpaActiveRecord
@RooJson
public class Departamento {
	private String nombre;
	
	@ManyToOne
	private Provincia provincia;
	
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
}
