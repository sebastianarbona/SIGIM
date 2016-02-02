package com.core.irrigacion.domain;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.core.irrigacion.domain.ubicacion.Localidad;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Empresa {
	private String razonSocial;
	private String cuit;
	
	@ManyToOne
	private Estado estado;
	
	private String calle;
	private String numero;
	private String piso;
	private String departamento;
	
	@ManyToOne
	private Localidad localidad;
	
	private String observacion;
	
	private String latitud;
	private String longitud;
	private String grados;
	private String minutos;
	private String segundos;
	
	private String email;
	private String telefono;
	private String telefonoAlt;
	private String celular;
	private String celularAlt;
	private String fax;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
}
