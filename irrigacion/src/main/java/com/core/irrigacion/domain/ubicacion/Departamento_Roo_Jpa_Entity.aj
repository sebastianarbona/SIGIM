// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain.ubicacion;

import com.core.irrigacion.domain.ubicacion.Departamento;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Departamento_Roo_Jpa_Entity {
    
    declare @type: Departamento: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Departamento.id;
    
    @Version
    @Column(name = "version")
    private Integer Departamento.version;
    
    public Long Departamento.getId() {
        return this.id;
    }
    
    public void Departamento.setId(Long id) {
        this.id = id;
    }
    
    public Integer Departamento.getVersion() {
        return this.version;
    }
    
    public void Departamento.setVersion(Integer version) {
        this.version = version;
    }
    
}
