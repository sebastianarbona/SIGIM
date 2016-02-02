// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain;

import com.core.irrigacion.domain.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Estado_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Estado.entityManager;
    
    public static final List<String> Estado.fieldNames4OrderClauseFilter = java.util.Arrays.asList("estado");
    
    public static final EntityManager Estado.entityManager() {
        EntityManager em = new Estado().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Estado.countEstadoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Estado o", Long.class).getSingleResult();
    }
    
    public static List<Estado> Estado.findAllEstadoes() {
        return entityManager().createQuery("SELECT o FROM Estado o", Estado.class).getResultList();
    }
    
    public static List<Estado> Estado.findAllEstadoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Estado o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Estado.class).getResultList();
    }
    
    public static Estado Estado.findEstado(Long id) {
        if (id == null) return null;
        return entityManager().find(Estado.class, id);
    }
    
    public static List<Estado> Estado.findEstadoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Estado o", Estado.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Estado> Estado.findEstadoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Estado o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Estado.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Estado.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Estado.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Estado attached = Estado.findEstado(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Estado.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Estado.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Estado Estado.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Estado merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
