package com.core.irrigacion.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class Acta {
	@Temporal(TemporalType.DATE)
	private Date fechaActa;
	
	private Long numActa;
	
	@ManyToOne
	private Inspeccion inspeccion;
	
	@ManyToOne
	private TipoCausa tipoCausa;

	private Long diasDescargo;
	
	private String observacion;
	
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	@Transient
	private String fechaActaStr;
	
	@Transient
	private String fechaVencimientoStr;
	
	@Transient
    public String getFechaActaStr() {
        if (fechaActa != null) {
        	fechaActaStr =  new SimpleDateFormat("dd/MM/yyyy").format(fechaActa);
            return fechaActaStr;
        } else {
            return "";
        }
    }	
	@Transient
    public String getFechaVencimientoStr() {
        if (fechaVencimiento != null) {
        	fechaVencimientoStr =  new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
            return fechaVencimientoStr;
        } else {
            return "";
        }
    }
}