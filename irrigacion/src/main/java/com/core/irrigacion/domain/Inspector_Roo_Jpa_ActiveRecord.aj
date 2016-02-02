// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain;

import com.core.irrigacion.domain.Inspector;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Inspector_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Inspector.entityManager;
    
    public static final List<String> Inspector.fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "apellido");
    
    public static final EntityManager Inspector.entityManager() {
        EntityManager em = new Inspector().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Inspector.countInspectors() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Inspector o", Long.class).getSingleResult();
    }
    
    public static List<Inspector> Inspector.findAllInspectors() {
        return entityManager().createQuery("SELECT o FROM Inspector o", Inspector.class).getResultList();
    }
    
    public static List<Inspector> Inspector.findAllInspectors(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Inspector o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Inspector.class).getResultList();
    }
    
    public static Inspector Inspector.findInspector(Long id) {
        if (id == null) return null;
        return entityManager().find(Inspector.class, id);
    }
    
    public static List<Inspector> Inspector.findInspectorEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Inspector o", Inspector.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Inspector> Inspector.findInspectorEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Inspector o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Inspector.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Inspector.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Inspector.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Inspector attached = Inspector.findInspector(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Inspector.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Inspector.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Inspector Inspector.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Inspector merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
