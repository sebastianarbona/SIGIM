package com.core.irrigacion.controller;

import java.util.ArrayList;
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

import com.core.irrigacion.domain.Empresa;
import com.core.irrigacion.domain.Estado;
import com.core.irrigacion.domain.Inspeccion;
import com.core.irrigacion.domain.Inspector;
import com.core.irrigacion.domain.Items;
import com.core.irrigacion.domain.Matafuego;
import com.core.irrigacion.util.ABMController;
import com.core.irrigacion.util.DateUtil;
import com.core.irrigacion.util.EntitiesExceptions;
import com.core.irrigacion.util.JSONUtil;

@Controller
@RequestMapping("/administracion/inspeccion/")
public class InspeccionController extends ABMController{
	
	@Autowired
	private InspeccionServices inspeccionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/buscar", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
	public @ResponseBody String buscarInspeccion(HttpServletResponse response, 
											 @RequestParam(required = false) String id,
											 @RequestParam(required = false) String fecha,
											 @RequestParam(required = false) String numInsp,
											 @RequestParam(required = false) String empresa,
											 @RequestParam(required = false) String estado) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		try {
			
			if(empresa != null && empresa.contains("\"")){
				empresa = empresa.replaceAll("\"","&#34;");
			}
			
			if(estado != null && estado.contains("\"")){
				estado = estado.replaceAll("\"","&#34;");
			}
				
			try{
				String consulta2 = "SELECT i FROM Inspeccion i WHERE i.fechaBaja IS NULL ";

				if(empresa != null && !empresa.isEmpty()){
					consulta2 = consulta2 + "AND i.empresa.razonSocial LIKE :razonSocial ";
				}
				
				if(estado != null && !estado.isEmpty()){
					consulta2 = consulta2 + "AND i.estado.estado LIKE :estado ";
				}

				if(fecha != null && !fecha.isEmpty()){
					consulta2 = consulta2 + "AND i.fechaInspeccion = fechaInspeccion ";
				}

				if(numInsp != null && !numInsp.isEmpty()){
					consulta2 = consulta2 + "AND i.numInsp LIKE :numInsp ";
				}

				if(id != null && !id.isEmpty()){
					consulta2 = consulta2 + "AND i.id = :id ";
				}
				
				consulta2 = consulta2 + "ORDER BY 1 DESC";
				
				Query query = Inspeccion.entityManager().createQuery(consulta2);

				if(estado != null && !estado.isEmpty()){
					query = query.setParameter("estado", "%" + estado + "%");
				}
				
				if(empresa != null && !empresa.isEmpty()){
					query = query.setParameter("razonSocial", "%" + empresa + "%");
				}

				if(fecha != null && !fecha.isEmpty()){
					Date fechaInspeccion = DateUtil.parseDate(fecha);
					query = query.setParameter("fechaInspeccion", fechaInspeccion);
				}

				if(numInsp != null && !numInsp.isEmpty()){
					query = query.setParameter("numInsp", "%" + numInsp + "%");
				}

				if(id != null && !id.isEmpty()){
					query = query.setParameter("id", new Long(id));
				}

				if(id != null && !id.isEmpty()){
					Inspeccion inspeccion = (Inspeccion) query.getSingleResult();
					if(inspeccion != null){
						return inspeccion.toJson();
					}else{
						return "[]";
					}
				}else{
					List<Inspeccion> inspecciones = query.getResultList();
					if(!inspecciones.isEmpty()){
						return Inspeccion.toJsonArray(inspecciones);
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/inspectores/buscar", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
	public @ResponseBody String buscarInspectores(HttpServletResponse response, 
											 @RequestParam(required = false) String id) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		try {
			try{
				if(id != null && !id.isEmpty()){
					String consulta2 = "SELECT i.inspector FROM Inspeccion i WHERE i.fechaBaja IS NULL ";

					consulta2 = consulta2 + "AND i.id = :id ";
				
					Query query = Inspeccion.entityManager().createQuery(consulta2);
				
					if(id != null && !id.isEmpty()){
						query = query.setParameter("id", new Long(id));
					}
					
					List<Inspector> inspectores = query.getResultList();
					
					if(!inspectores.isEmpty()){
						return Inspector.toJsonArray(inspectores);
					}else{
						return "[]";
					}
				}else{
					return "[]";
				}

			} catch(Exception e){
				return "[]";
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}
    

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/items/buscar", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
	public @ResponseBody String buscarItems(HttpServletResponse response, 
											 @RequestParam(required = false) String id) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		try {
			try{
				if(id != null && !id.isEmpty()){
					String consulta2 = "SELECT i.items FROM Inspeccion i WHERE i.fechaBaja IS NULL ";
		
					consulta2 = consulta2 + "AND i.id = :id ";
					
					Query query = Inspeccion.entityManager().createQuery(consulta2);
					
					if(id != null && !id.isEmpty()){
						query = query.setParameter("id", new Long(id));
					}
					
					List<Items> items = query.getResultList();
					
					if(!items.isEmpty()){
						return Items.toJsonArray(items);
					}else{
						return "[]";
					}
				}else{
					return "[]";
				}
			} catch(Exception e){
				return "[]";
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}
    

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/matafuegos/buscar", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
	public @ResponseBody String buscarMatafuegos(HttpServletResponse response, 
											 @RequestParam(required = false) String id) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		try {
			try{
				if(id != null && !id.isEmpty()){
					String consulta2 = "SELECT i.matafuego FROM Inspeccion i WHERE i.fechaBaja IS NULL ";
		
					if(id != null && !id.isEmpty()){
						consulta2 = consulta2 + "AND i.id = :id ";
					}
					
					Query query = Inspeccion.entityManager().createQuery(consulta2);
					
					if(id != null && !id.isEmpty()){
						query = query.setParameter("id", new Long(id));
					}
					
					List<Matafuego> matafuegos = query.getResultList();
					
					if(!matafuegos.isEmpty()){
						return Matafuego.toJsonArray(matafuegos);
					}else{
						return "[]";
					}
				}else{
					return "[]";
				}
			} catch(Exception e){
				return "[]";
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}
    
    @RequestMapping(value = "/empresa/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String inspeccionCreate1(@RequestParam(required = false) String id, @RequestParam(required = false) String fechaInsp,
											   @RequestParam(required = false) Long numInspeccion, @RequestParam(required = false) String idEmpresa, 
											   @RequestParam(required = false) String idInspeccionAnt, @RequestParam(required = false) String horaInicio, 
											   @RequestParam(required = false) String horaFin, @RequestParam(required = false) String idEstado, 
											   @RequestParam(required = false) String observacion, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

		Inspeccion inspeccion = null;

		if (!isIDNull(id)) {
			Long idInspeccion = new Long(id);
			inspeccion = Inspeccion.findInspeccion(idInspeccion);
		} else {
			inspeccion = new Inspeccion();
		}
		Date fecha = DateUtil.parseDate(fechaInsp);
		
		try{
		List<Inspeccion> ultimaInspeccion = inspeccionService.buscarUltimaInspeccion();
		
		if(ultimaInspeccion.isEmpty()){
			if(fecha.after(new Date())){
				return JSONUtil.getErrorJSON("La fecha de la inspeccion no puede ser posterior al dia de la Fecha.");
			}else{
				inspeccion.setFechaInspeccion(fecha);
			}
		}else{
			Date fechaUltInsp = ultimaInspeccion.get(0).getFechaInspeccion();
			if(fecha.before(fechaUltInsp)){
				return JSONUtil.getErrorJSON("La fecha de la inspeccion no puede ser anterior a la de la Ultima inspeccion.");
			}else if(fecha.after(new Date())){
				return JSONUtil.getErrorJSON("La fecha de la inspeccion no puede ser posterior al dia de la Fecha.");
			}else{
				inspeccion.setFechaInspeccion(fecha);
			}
		}
		inspeccion.setNumInspeccion(numInspeccion);
		
		Empresa empresa = Empresa.findEmpresa(new Long(idEmpresa));
		inspeccion.setEmpresa(empresa);
		
		Inspeccion inspeccionAnt = Inspeccion.findInspeccion(new Long(idInspeccionAnt));
		inspeccion.setInspeccionAnt(inspeccionAnt);
		
		String[] horaInicioArray = horaInicio.split(":");
		String[] horaFinArray = horaInicio.split(":");
		if(new Long(horaInicioArray[0]) < new Long(horaFinArray[0])){
			return JSONUtil.getErrorJSON("La hora de fin de la inspeccion no puede ser posterior a la hora de inicio.");
		} else if(new Long(horaInicioArray[0]) == new Long(horaFinArray[0])){
			if(new Long(horaInicioArray[1]) > new Long(horaFinArray[1])){
				return JSONUtil.getErrorJSON("La hora de fin de la inspeccion no puede ser posterior a la hora de inicio.");
			}else{
				inspeccion.setHoraInicio(horaInicio);
				inspeccion.setHoraFin(horaFin);
			}
		}else {
			inspeccion.setHoraInicio(horaInicio);
			inspeccion.setHoraFin(horaFin);
		}
		
		Estado estado = Estado.findEstado(new Long(idEstado));
		inspeccion.setEstado(estado);
		
		inspeccion = inspeccionService.saveInspeccion(inspeccion);
		
		return inspeccion.toJson();
		
		}catch(EntitiesExceptions e){
			return JSONUtil.getErrorJSON("error");
		}
	}

	@RequestMapping(value = "/inspector/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String inspeccionCreate2(@RequestParam(required = false) String id, @RequestParam(required = false) String idInspector, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

		try {		
			Inspeccion inspeccion = null;
	
			try {
				Long idInspeccion = new Long(id);
				inspeccion = Inspeccion.findInspeccion(idInspeccion);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			List<Inspector> inspectorList = null;
			
			inspectorList = inspeccion.getInspector();
			
			if(inspectorList == null){
				inspectorList = new ArrayList<Inspector>();
			}
			
			Inspector inspector = Inspector.findInspector(new Long(idInspector));
			for (Inspector inspectorL : inspectorList) {
				if(inspectorL.getId().toString().equals(idInspector)){
					return JSONUtil.getErrorJSON("existe");
				}
			}
			inspectorList.add(inspector);
			
			inspeccion.setInspector(inspectorList);
			
			inspeccion = inspeccionService.saveInspeccion(inspeccion);
			return inspeccion.toJson();
		
		} catch (EntitiesExceptions e) {
			e.printStackTrace();
			return JSONUtil.getErrorJSON("error");
		}
	}
	
	@RequestMapping(value = "/items/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String inspeccionCreate3(@RequestParam(required = false) String id, @RequestParam(required = false) String codigo,
											@RequestParam(required = false) String denominacion, @RequestParam(required = false) String tipo, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

		try{
			
			Inspeccion inspeccion = null;
	
			try {
				Long idInspeccion= new Long(id);
				inspeccion = Inspeccion.findInspeccion(idInspeccion);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			Items items = new Items();
			
			items.setCodigo(codigo);
			items.setDenominacion(denominacion);
			items.setTipo(tipo);
			
			List<Items> itemsList = null;
			
			itemsList = inspeccion.getItems();
			
			if(itemsList == null){
				itemsList = new ArrayList<Items>();
			}
			items = items.merge();
			
			itemsList.add(items);
			inspeccion.setItems(itemsList);
			
			inspeccion = inspeccionService.saveInspeccion(inspeccion);
			return inspeccion.toJson();
		
		} catch (EntitiesExceptions e) {
			e.printStackTrace();
			return JSONUtil.getErrorJSON("error");
		}
	}
	
	@RequestMapping(value = "/matafuegos/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String inspeccionCreate4(@RequestParam(required = false) String id, @RequestParam(required = false) Long numMatafuego,
											@RequestParam(required = false) Long numHabilitacion, @RequestParam(required = false) String capacidad, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");


		try{
			Inspeccion inspeccion = null;

			try {
				Long idInspeccion= new Long(id);
				inspeccion = Inspeccion.findInspeccion(idInspeccion);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			Matafuego matafuego = new Matafuego();
			
			matafuego.setNumHabilitacion(numHabilitacion);
			matafuego.setNumMatafuego(numMatafuego);
			matafuego.setCapacidad(capacidad);
			
			List<Matafuego> matafuegoList = null;
			
			matafuegoList = inspeccion.getMatafuego();
			
			if(matafuegoList == null){
				matafuegoList = new ArrayList<Matafuego>();
			}
			matafuego = matafuego.merge();
			
			matafuegoList.add(matafuego);
			inspeccion.setMatafuego(matafuegoList);
			
			inspeccion = inspeccionService.saveInspeccion(inspeccion);
			return inspeccion.toJson();
		
		} catch (EntitiesExceptions e) {
			e.printStackTrace();
			return JSONUtil.getErrorJSON("error");
		}
	}
	
	@RequestMapping(value = "/eliminar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String inspeccionEliminar(@RequestParam(required = false) String id, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

			Inspeccion inspeccion = null;
			try {
				Long idInspeccion= new Long(id);
				inspeccion = Inspeccion.findInspeccion(idInspeccion);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			List<Inspector> inspectorList = null;
			
			inspectorList = inspeccion.getInspector();
			
			List<Items> itemsList = null;
			
			itemsList = inspeccion.getItems();
			
			List<Matafuego> matafuegoList = null;
			
			matafuegoList = inspeccion.getMatafuego();
			
			if(matafuegoList != null){
				for (Matafuego matafuego : matafuegoList) {
					matafuego.remove();	
				}
			}
			
			if(itemsList != null){
				for (Items items : itemsList) {
					items.remove();	
				}
			}

			if(inspectorList != null){
				for (Inspector inspector : inspectorList) {
					inspector.remove();	
				}
			}
			
			inspeccion.remove();
			return inspeccion.toJson();
		
	}
	

	@RequestMapping(value = "/inspector/eliminar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String inspectorEliminar(@RequestParam(required = false) String id, @RequestParam(required = false) String idInspector, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

		try{
			Inspeccion inspeccion = null;
			Inspector inspector = null;
			try {
				Long idInspeccion= new Long(id);
				inspeccion = Inspeccion.findInspeccion(idInspeccion);
				inspector = Inspector.findInspector(new Long(idInspector));
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			List<Inspector> inspectorList = null;
			
			inspectorList = inspeccion.getInspector();
			
			inspectorList.remove(inspector);
			
			inspeccion = inspeccionService.saveInspeccion(inspeccion);
			return inspeccion.toJson();
		
		} catch (EntitiesExceptions e) {
			e.printStackTrace();
			return JSONUtil.getErrorJSON("error");
		}
	}
	
	@RequestMapping(value = "/items/eliminar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String itemsEliminar(@RequestParam(required = false) String id, @RequestParam(required = false) String idItems, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

		try{
			Inspeccion inspeccion = null;
			Items items = null;
			try {
				Long idInspeccion= new Long(id);
				inspeccion = Inspeccion.findInspeccion(idInspeccion);
				items = Items.findItems(new Long(idItems));
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			List<Items> itemsList = null;
			
			itemsList = inspeccion.getItems();
			
			itemsList.remove(items);
			items.remove();

			inspeccion = inspeccionService.saveInspeccion(inspeccion);
			return inspeccion.toJson();
		
		} catch (EntitiesExceptions e) {
			e.printStackTrace();
			return JSONUtil.getErrorJSON("error");
		}
	}
	
	@RequestMapping(value = "/matafuegos/eliminar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String matafuegosEliminar(@RequestParam(required = false) String id, @RequestParam(required = false) String idMatafuego, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

		try{
			Inspeccion inspeccion = null;
			Matafuego matafuego = null;
			try {
				Long idInspeccion= new Long(id);
				inspeccion = Inspeccion.findInspeccion(idInspeccion);
				matafuego = Matafuego.findMatafuego(new Long(idMatafuego));
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			List<Matafuego> matafuegoList = null;
			
			matafuegoList = inspeccion.getMatafuego();
			
			matafuegoList.remove(matafuego);
			
			matafuego.remove();
			
			inspeccion = inspeccionService.saveInspeccion(inspeccion);
			return inspeccion.toJson();
		
		} catch (EntitiesExceptions e) {
			e.printStackTrace();
			return JSONUtil.getErrorJSON("error");
		}
	}
}