package com.core.irrigacion.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.core.irrigacion.domain.Inspeccion;
import com.core.irrigacion.util.EntitiesExceptions;
import com.core.irrigacion.util.ServicesABM;
@Service
public class InspeccionServices extends ServicesABM{
	public Inspeccion saveInspeccion(Inspeccion i) throws EntitiesExceptions {
		if (validarInspeccion(i)) {
			if (isIdNull(i.getId())) {
				i.setFechaAlta(new Date());
				i.persist();  
			}else{			
			i = i.merge();
			}
		}
		
		return i;
	}

	public Inspeccion deleteInspeccion(Inspeccion i) throws EntitiesExceptions {
		i.setFechaBaja(new Date());
		i = saveInspeccion(i);
		return i;
	}

	private boolean validarInspeccion(Inspeccion i) throws EntitiesExceptions {
		
//		if (i.getCantidad() == null ) {
//			throw new EntitiesExceptions("El campo Cantidad es obligatorio");
//		}
//		
//		if (i.getProducto() == null ) {
//			throw new EntitiesExceptions("El campo Producto es obligatorio");
//		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Inspeccion> buscarUltimaInspeccion() {
		Query sql = Inspeccion.entityManager().createQuery("SELECT i FROM Inspeccion i "
				+ "ORDER BY i.fechaInspeccion DESC");

		List<Inspeccion> uf = sql.setMaxResults(1).getResultList();

		return uf;
	}
}
