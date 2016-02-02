package com.core.irrigacion.util;


import javax.persistence.EntityManager;


public class ServicesABM {
	
	public boolean isIdNull(Long id){
		return id == null || id == 0;
	}
	
	public void existe(Object nombre, Long id, Class clase, EntityManager em) throws EntitiesExceptions {
		existe("nombre", nombre,  id, clase, em);
	}
	
	@SuppressWarnings("rawtypes")
	public void existe(String atributo, Object nombre, Long id, Class clase, EntityManager em) throws EntitiesExceptions {

		Object resultado = null;
		
		try {
			if (id != null && id != 0) {
				resultado = em.createQuery("SELECT o FROM " + clase.getSimpleName()+ " o WHERE o." + atributo 
							+ " = :nombre AND NOT o.id = :id").setParameter("nombre", nombre)
							.setParameter("id", id).getSingleResult();
			} else {
				resultado = em.createQuery("SELECT o FROM " + clase.getSimpleName() + " o WHERE o." + atributo + " = :nombre")
							.setParameter("nombre", nombre).getSingleResult();
			}
		} catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
		}

		if (resultado != null) {
			throw new EntitiesExceptions("El "+ atributo + " que intenta ingresar ya existe");
		}
	}

	

  }
	
	

