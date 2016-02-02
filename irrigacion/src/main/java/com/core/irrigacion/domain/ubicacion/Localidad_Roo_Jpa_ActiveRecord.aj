// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain.ubicacion;

import com.core.irrigacion.domain.ubicacion.Localidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Localidad_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Localidad.entityManager;
    
    public static final List<String> Localidad.fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "codigoP", "fechaBaja", "departamento");
    
    public static final EntityManager Localidad.entityManager() {
        EntityManager em = new Localidad().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Localidad.countLocalidads() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Localidad o", Long.class).getSingleResult();
    }
    
    public static List<Localidad> Localidad.findAllLocalidads() {
        return entityManager().createQuery("SELECT o FROM Localidad o", Localidad.class).getResultList();
    }
    
    public static List<Localidad> Localidad.findAllLocalidads(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Localidad o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Localidad.class).getResultList();
    }
    
    public static Localidad Localidad.findLocalidad(Long id) {
        if (id == null) return null;
        return entityManager().find(Localidad.class, id);
    }
    
    public static List<Localidad> Localidad.findLocalidadEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Localidad o", Localidad.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Localidad> Localidad.findLocalidadEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Localidad o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Localidad.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Localidad.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Localidad.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Localidad attached = Localidad.findLocalidad(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Localidad.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Localidad.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Localidad Localidad.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Localidad merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}