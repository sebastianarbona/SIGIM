package com.core.irrigacion.controller;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.core.irrigacion.domain.Acta;
import com.core.irrigacion.util.EntitiesExceptions;
import com.core.irrigacion.util.ServicesABM;

@Service
public class ActaServices extends ServicesABM {
	public Acta saveActa(Acta i) throws EntitiesExceptions {
		if (validarActa(i)) {
			if (isIdNull(i.getId())) {
				i.setFechaAlta(new Date());
				i.persist();  
			}else{			
			i = i.merge();
			}
		}
		
		return i;
	}

	public Acta deleteActa(Acta i) throws EntitiesExceptions {
		i.setFechaBaja(new Date());
		i = saveActa(i);
		return i;
	}

	private boolean validarActa(Acta i) throws EntitiesExceptions {
		
//		if (i.getCantidad() == null ) {
//			throw new EntitiesExceptions("El campo Cantidad es obligatorio");
//		}
//		
//		if (i.getProducto() == null ) {
//			throw new EntitiesExceptions("El campo Producto es obligatorio");
//		}
		return true;
	}

//	@SuppressWarnings("unchecked")
//	public List<Acta> buscarUltimaActa() {
//		Query sql = Acta.entityManager().createQuery("SELECT i FROM Acta i "
//				+ "ORDER BY i.fechaActa DESC LIMIT 1");
//
//		List<Acta> uf = sql.getResultList();
//
//		return uf;
//	}
}
