// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain;

import com.core.irrigacion.domain.Empresa;
import com.core.irrigacion.domain.Estado;
import com.core.irrigacion.domain.Inspeccion;
import com.core.irrigacion.domain.Inspector;
import com.core.irrigacion.domain.Items;
import com.core.irrigacion.domain.Matafuego;
import java.util.Date;
import java.util.List;

privileged aspect Inspeccion_Roo_JavaBean {
    
    public Date Inspeccion.getFechaInspeccion() {
        return this.fechaInspeccion;
    }
    
    public void Inspeccion.setFechaInspeccion(Date fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }
    
    public Long Inspeccion.getNumInspeccion() {
        return this.numInspeccion;
    }
    
    public void Inspeccion.setNumInspeccion(Long numInspeccion) {
        this.numInspeccion = numInspeccion;
    }
    
    public Empresa Inspeccion.getEmpresa() {
        return this.empresa;
    }
    
    public void Inspeccion.setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public Inspeccion Inspeccion.getInspeccionAnt() {
        return this.inspeccionAnt;
    }
    
    public void Inspeccion.setInspeccionAnt(Inspeccion inspeccionAnt) {
        this.inspeccionAnt = inspeccionAnt;
    }
    
    public String Inspeccion.getHoraInicio() {
        return this.horaInicio;
    }
    
    public void Inspeccion.setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public String Inspeccion.getHoraFin() {
        return this.horaFin;
    }
    
    public void Inspeccion.setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    
    public Estado Inspeccion.getEstado() {
        return this.estado;
    }
    
    public void Inspeccion.setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public String Inspeccion.getObservacion() {
        return this.observacion;
    }
    
    public void Inspeccion.setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public List<Inspector> Inspeccion.getInspector() {
        return this.inspector;
    }
    
    public void Inspeccion.setInspector(List<Inspector> inspector) {
        this.inspector = inspector;
    }
    
    public List<Items> Inspeccion.getItems() {
        return this.items;
    }
    
    public void Inspeccion.setItems(List<Items> items) {
        this.items = items;
    }
    
    public List<Matafuego> Inspeccion.getMatafuego() {
        return this.matafuego;
    }
    
    public void Inspeccion.setMatafuego(List<Matafuego> matafuego) {
        this.matafuego = matafuego;
    }
    
    public Date Inspeccion.getFechaBaja() {
        return this.fechaBaja;
    }
    
    public void Inspeccion.setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    
    public Date Inspeccion.getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void Inspeccion.setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    public void Inspeccion.setFechaInspeccionStr(String fechaInspeccionStr) {
        this.fechaInspeccionStr = fechaInspeccionStr;
    }
    
}
