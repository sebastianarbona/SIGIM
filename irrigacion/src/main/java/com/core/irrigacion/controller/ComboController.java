package com.core.irrigacion.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.irrigacion.domain.Empresa;
import com.core.irrigacion.domain.Estado;
import com.core.irrigacion.domain.Inspeccion;
import com.core.irrigacion.domain.Inspector;
import com.core.irrigacion.domain.TipoCausa;
import com.core.irrigacion.domain.ubicacion.Localidad;
import com.core.irrigacion.util.ABMController;

@Controller
@RequestMapping("/administracion/combo/")
public class ComboController extends ABMController{

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tipocausa", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String comboTipoCausa(HttpServletResponse response, ModelMap model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		List<TipoCausa> tp = null;
		
		tp = TipoCausa.entityManager().createQuery("SELECT c FROM TipoCausa c").getResultList();
		
		return TipoCausa.toJsonArray(tp);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/estado", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String comboEstado(HttpServletResponse response, ModelMap model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		List<Estado> estados = null;
		
		estados = Estado.entityManager().createQuery("SELECT c FROM Estado c").getResultList();
		
		return Estado.toJsonArray(estados);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/localidad", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String comboLocalidad(HttpServletResponse response, ModelMap model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		List<Localidad> localidades = null;
		
		localidades = Localidad.entityManager().createQuery("SELECT c FROM Localidad c").getResultList();
		
		return Localidad.toJsonArray(localidades);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/empresa", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String comboEmpresa(HttpServletResponse response, ModelMap model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		List<Empresa> empresas = null;
		
		empresas = Empresa.entityManager().createQuery("SELECT c FROM Empresa c").getResultList();
		
		return Empresa.toJsonArray(empresas);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/inspeccion", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String comboInspeccion(HttpServletResponse response, ModelMap model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		List<Inspeccion> inspecciones = null;
		
		inspecciones = Inspeccion.entityManager().createQuery("SELECT c FROM Inspeccion c").getResultList();
		
		return Inspeccion.toJsonArray(inspecciones);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/inspector", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody String comboInspector(HttpServletResponse response, ModelMap model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		List<Inspector> inspectores = null;
		
		inspectores = Inspector.entityManager().createQuery("SELECT c FROM Inspector c").getResultList();
		
		return Inspector.toJsonArray(inspectores);
	}
}
