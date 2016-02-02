package com.core.irrigacion.controller;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.core.irrigacion.domain.Empresa;
import com.core.irrigacion.util.EntitiesExceptions;
import com.core.irrigacion.util.ServicesABM;

@Service
public class EmpresaServices extends ServicesABM {
	public Empresa saveEmpresa(Empresa i) throws EntitiesExceptions {
		if (validarEmpresa(i)) {
			if (isIdNull(i.getId())) {
				i.setFechaAlta(new Date());
				i.persist();  
			}else{			
			i = i.merge();
			}
		}
		
		return i;
	}

	public Empresa deleteEmpresa(Empresa i) throws EntitiesExceptions {
		i.setFechaBaja(new Date());
		i = saveEmpresa(i);
		return i;
	}

	private boolean validarEmpresa(Empresa i) throws EntitiesExceptions {
		
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
//	public List<Empresa> buscarUltimaEmpresa() {
//		Query sql = Empresa.entityManager().createQuery("SELECT i FROM Empresa i "
//				+ "ORDER BY i.fechaEmpresa DESC LIMIT 1");
//
//		List<Empresa> uf = sql.getResultList();
//
//		return uf;
//	}
}
