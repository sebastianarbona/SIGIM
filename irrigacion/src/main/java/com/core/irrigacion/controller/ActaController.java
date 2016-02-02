package com.core.irrigacion.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.irrigacion.domain.Acta;
import com.core.irrigacion.domain.Empresa;
import com.core.irrigacion.domain.Inspeccion;
import com.core.irrigacion.domain.TipoCausa;
import com.core.irrigacion.util.ABMController;
import com.core.irrigacion.util.DateUtil;
import com.core.irrigacion.util.EntitiesExceptions;
import com.core.irrigacion.util.JSONUtil;

@Controller
@RequestMapping("/administracion/acta/")
public class ActaController extends ABMController{
	
	@Autowired
	private ActaServices actaService;
	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/buscar", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
	public @ResponseBody String buscarActa(HttpServletResponse response, 
											 @RequestParam(required = false) String id,
											 @RequestParam(required = false) String fechaActa,
											 @RequestParam(required = false) String numActa,
											 @RequestParam(required = false) String fechaVencimiento) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		try {
			try{
				String consulta2 = "SELECT e FROM Acta e WHERE e.fechaBaja IS NULL ";
				
				if(numActa != null && !numActa.isEmpty()){
					consulta2 = consulta2 + "AND e.numActa LIKE :numActa ";
				}
				
				if(fechaActa != null && !fechaActa.isEmpty()){
					consulta2 = consulta2 + "AND e.fechaActa = :fechaActa ";
				}
				
				if(fechaVencimiento != null && !fechaVencimiento.isEmpty()){
					consulta2 = consulta2 + "AND e.fechaVencimiento = :fechaVencimiento ";
				}
				
				if(id != null && !id.isEmpty()){
					consulta2 = consulta2 + "AND e.id = :id ";
				}
				
				consulta2 = consulta2 + "ORDER BY 1 DESC";
				
				Query query = Empresa.entityManager().createQuery(consulta2);
				
				if(numActa != null && !numActa.isEmpty()){
					query = query.setParameter("numActa", "%" + numActa + "%");
				}

				if(fechaActa != null && !fechaActa.isEmpty()){
					query = query.setParameter("fechaActa", fechaActa);
				}

				if(fechaVencimiento != null && !fechaVencimiento.isEmpty()){
					query = query.setParameter("fechaVencimiento", fechaVencimiento);
				}
				
				if(id != null && !id.isEmpty()){
					query = query.setParameter("id", new Long(id));
				}
				
				if(id != null && !id.isEmpty()){
					Acta acta = (Acta) query.getSingleResult();
					if(acta != null){
						return acta.toJson();
					}else{
						return "[]";
					}
				}else{
					List<Acta> actas = query.getResultList();
					if(!actas.isEmpty()){
						return Acta.toJsonArray(actas);
					}else{
						return "[]";
					}
				}
				
			} catch(Exception e){
				return "[]";
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}
 
    @RequestMapping(value = "/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String actaCreate1(@RequestParam(required = false) String id,
											   @RequestParam(required = false) String fechaActa,
											   @RequestParam(required = false) Long numActa,
											   @RequestParam(required = false) String idInspeccion,
											   @RequestParam(required = false) String idTipoCausa,
											   @RequestParam(required = false) String fechaVencimiento,
											   @RequestParam(required = false) Long diasDescargo,
											   @RequestParam(required = false) String observacion, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		
    	try{
    		
			Acta acta = null;
	
			if (!isIDNull(id)) {
				Long idActa= new Long(id);
				acta = Acta.findActa(idActa);
			} else {
				acta = new Acta();
			}

			Date fechaA = DateUtil.parseDate(fechaActa);
			acta.setFechaActa(fechaA);
			acta.setNumActa(numActa);
			
			Inspeccion inspeccion = Inspeccion.findInspeccion(new Long(idInspeccion));
			acta.setInspeccion(inspeccion);
			
			TipoCausa tp = TipoCausa.findTipoCausa(new Long(idTipoCausa));
			acta.setTipoCausa(tp);

			Date fechaV = DateUtil.parseDate(fechaVencimiento);
			acta.setFechaVencimiento(fechaV);
			acta.setDiasDescargo(diasDescargo);
			acta.setObservacion(observacion);
			
			acta = actaService.saveActa(acta);
			
			return acta.toJson();
		}catch(EntitiesExceptions e){
			return JSONUtil.getErrorJSON("error");
		}

	}

	@RequestMapping(value = "/eliminar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String actaEliminar(@RequestParam(required = false) String id, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

			Acta acta = null;
			try {
				Long idActa= new Long(id);
				acta = Acta.findActa(idActa);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			acta.remove();
			return acta.toJson();
		
	}

}