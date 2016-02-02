package com.core.irrigacion.controller;

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
import com.core.irrigacion.domain.ubicacion.Localidad;
import com.core.irrigacion.util.ABMController;
import com.core.irrigacion.util.EntitiesExceptions;
import com.core.irrigacion.util.JSONUtil;

@Controller
@RequestMapping("/administracion/empresa/")
public class EmpresaController extends ABMController{
	
	@Autowired
	private EmpresaServices empresaService;
	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/buscar", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
	public @ResponseBody String buscarEmpresa(HttpServletResponse response, 
											 @RequestParam(required = false) String id,
											 @RequestParam(required = false) String cuit,
											 @RequestParam(required = false) String razonSocial,
											 @RequestParam(required = false) String numTel,
											 @RequestParam(required = false) String email) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		try {
			
			if(razonSocial != null && razonSocial.contains("\"")){
				razonSocial = razonSocial.replaceAll("\"","&#34;");
			}

			if(numTel != null && numTel.contains("\"")){
				numTel = numTel.replaceAll("\"","&#34;");
			}
				
			try{
				String consulta2 = "SELECT e FROM Empresa e WHERE e.fechaBaja IS NULL ";
				
				if(numTel != null && !numTel.isEmpty()){
					consulta2 = consulta2 + "AND e.numTel LIKE :numTel ";
				}
				
				if(razonSocial != null && !razonSocial.isEmpty()){
					consulta2 = consulta2 + "AND e.razonSocial LIKE :razonSocial ";
				}
				
				if(email != null && !email.isEmpty()){
					consulta2 = consulta2 + "AND e.email LIKE :email ";
				}
				
				if(cuit != null && !cuit.isEmpty()){
					consulta2 = consulta2 + "AND e.cuit LIKE :cuit ";
				}
				
				if(id != null && !id.isEmpty()){
					consulta2 = consulta2 + "AND e.id = :id ";
				}
				
				consulta2 = consulta2 + "ORDER BY 1 DESC";
				
				Query query = Empresa.entityManager().createQuery(consulta2);
				
				if(numTel != null && !numTel.isEmpty()){
					query = query.setParameter("numTel", "%" + numTel + "%");
				}

				if(razonSocial != null && !razonSocial.isEmpty()){
					query = query.setParameter("razonSocial", "%" + razonSocial + "%");
				}

				if(email != null && !email.isEmpty()){
					query = query.setParameter("email", "%" + email + "%");
				}
				
				if(cuit != null && !cuit.isEmpty()){
					query = query.setParameter("cuit", "%" + cuit + "%");
				}
				
				if(id != null && !id.isEmpty()){
					query = query.setParameter("id", new Long(id));
				}
				
				if(id != null && !id.isEmpty()){
					Empresa empresa = (Empresa) query.getSingleResult();
					if(empresa != null){
						return empresa.toJson();
					}else{
						return "[]";
					}
				}else{
					List<Empresa> empresas = query.getResultList();
					if(!empresas.isEmpty()){
						return Empresa.toJsonArray(empresas);
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
 
    @RequestMapping(value = "/empresa/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String empresaCreate1(@RequestParam(required = false) String id, @RequestParam(required = false) String razonSocial,
											   @RequestParam(required = false) String cuit, @RequestParam(required = false) String idEstado, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		
    	try{
    		
			Empresa empresa = null;
	
			if (!isIDNull(id)) {
				Long idEmpresa = new Long(id);
				empresa = Empresa.findEmpresa(idEmpresa);
			} else {
				empresa = new Empresa();
			}
			
			empresa.setRazonSocial(razonSocial);
			empresa.setCuit(cuit);
			
			Estado estado = Estado.findEstado(new Long(idEstado));
			empresa.setEstado(estado);
			
			empresa = empresaService.saveEmpresa(empresa);
			
			return empresa.toJson();
		}catch(EntitiesExceptions e){
			return JSONUtil.getErrorJSON("error");
		}

	}

	@RequestMapping(value = "/domicilio/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String empresaCreate2(@RequestParam(required = false) String id, @RequestParam(required = false) String calle,
											@RequestParam(required = false) String numero, @RequestParam(required = false) String piso, 
											@RequestParam(required = false) String departamento, @RequestParam(required = false) String idLocalidad,
											@RequestParam(required = false) String observacion, @RequestParam(required = false) String latitud,
											@RequestParam(required = false) String longitud, @RequestParam(required = false) String grados,
											@RequestParam(required = false) String minutos, @RequestParam(required = false) String segundos, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		
		try{
			Empresa empresa = null;
	
			try {
				Long idEmpresa = new Long(id);
				empresa = Empresa.findEmpresa(idEmpresa);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			empresa.setCalle(calle);
			empresa.setNumero(numero);
			empresa.setPiso(piso);
			empresa.setDepartamento(departamento);
			
			Localidad localidad = Localidad.findLocalidad(new Long(idLocalidad));
			empresa.setLocalidad(localidad);
			
			empresa.setObservacion(observacion);
			if(grados.isEmpty() && minutos.isEmpty() && segundos.isEmpty() && latitud.isEmpty() && longitud.isEmpty()){
				return JSONUtil.getErrorJSON("vacio");
			} else if((grados == null && minutos == null && segundos == null) || (grados.isEmpty() && minutos.isEmpty() && segundos.isEmpty())){
				empresa.setLatitud(latitud);
				empresa.setLongitud(longitud);
			} else if ((latitud == null && longitud == null) || (latitud.isEmpty() && longitud.isEmpty())){
				empresa.setGrados(grados);
				empresa.setMinutos(minutos);
				empresa.setSegundos(segundos);
			}
			
			empresa = empresaService.saveEmpresa(empresa);
			
			return empresa.toJson();
		}catch(EntitiesExceptions e){
			return JSONUtil.getErrorJSON("error");
		}
	}
	
	@RequestMapping(value = "/contacto/modificar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String empresaCreate3(@RequestParam(required = false) String id, @RequestParam(required = false) String telefono,
											@RequestParam(required = false) String telefonoAlt, @RequestParam(required = false) String celular, 
											@RequestParam(required = false) String celularAlt, @RequestParam(required = false) String email,
											@RequestParam(required = false) String fax, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		
		try{
			Empresa empresa = null;
	
			try {
				Long idEmpresa = new Long(id);
				empresa = Empresa.findEmpresa(idEmpresa);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			empresa.setEmail(email);
			empresa.setTelefono(telefono);
			empresa.setTelefonoAlt(telefonoAlt);
			empresa.setCelular(celular);
			empresa.setCelularAlt(celularAlt);
			empresa.setFax(fax);
			
			empresa = empresaService.saveEmpresa(empresa);
			
			return empresa.toJson();
		}catch(EntitiesExceptions e){
			return JSONUtil.getErrorJSON("error");
		}
	}

	@RequestMapping(value = "/eliminar", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String empresaEliminar(@RequestParam(required = false) String id, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");

			Empresa empresa = null;
			try {
				Long idEmpresa= new Long(id);
				empresa = Empresa.findEmpresa(idEmpresa);
			} catch (Exception e){
				return JSONUtil.getErrorJSON("error");
			}
			
			empresa.remove();
			return empresa.toJson();
		
	}

}