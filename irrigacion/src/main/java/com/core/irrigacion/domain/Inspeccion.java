package com.core.irrigacion.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Inspeccion {
	@Temporal(TemporalType.DATE)
	private Date fechaInspeccion;
	
	private Long numInspeccion;
	
	@ManyToOne
	private Empresa empresa;
	
	@ManyToOne
	private Inspeccion inspeccionAnt;
	
	private String horaInicio;
	private String horaFin;
	
	@ManyToOne
	private Estado estado;
	
	private String observacion;
	
	@ManyToMany
	private List<Inspector> inspector;
	
	@ManyToMany
	private List<Items> items;
	
	@ManyToMany
	private List<Matafuego> matafuego;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Transient
	private String fechaInspeccionStr;
	
	@Transient
    public String getFechaInspeccionStr() {
        if (fechaInspeccion != null) {
        	fechaInspeccionStr =  new SimpleDateFormat("dd/MM/yyyy").format(fechaInspeccion);
            return fechaInspeccionStr;
        } else {
            return "";
        }
    }
}
