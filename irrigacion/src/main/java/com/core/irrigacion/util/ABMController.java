package com.core.irrigacion.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtilsBean;

import flexjson.JSONSerializer;

public class ABMController {
	

	
	@SuppressWarnings("rawtypes")
	private String armarConsulta(Class clase, EntityManager em, Busqueda busqueda){
		StringBuilder consulta = new StringBuilder();
		
		consulta.append("SELECT c FROM ").append(clase.getSimpleName()).append(" c WHERE c.fechaBaja IS NULL");
		
		
		if(busqueda.getFilters() != null){
			Filtro filtro = Filtro.fromJsonToFiltro(busqueda.getFilters());
			if(filtro != null && filtro.getRules() != null){
				
				if(filtro.getGroupOp().equals("AND")){
					consulta.append(" WHERE 1 = 1 ");
				} else {
					consulta.append(" WHERE 1 = 0 ");
				}
				
				for(Regla regla : filtro.getRules()){
					if(regla.getField().equals("myac")){
						continue;
					}
					
					consulta.append(filtro.getGroupOp());
					consulta.append(" c.").append(regla.getField());
					
					boolean isPrimitivo = false;
					boolean isFecha = false;
					
					try {
						Field field = clase.getDeclaredField(regla.getField());
						isPrimitivo = field.getType().isPrimitive(); 
						isFecha = field.getType().equals(Date.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					if(regla.getOp().equals("eq")){
						consulta.append( " = ");
					} else if(regla.getOp().equals("ne")){
						consulta.append( " != ");
					} else if(regla.getOp().equals("lt")){
						consulta.append( " < ");
					} else if(regla.getOp().equals("le")){
						consulta.append( " <= ");
					} else if(regla.getOp().equals("ge")){
						consulta.append( " >= ");
					} else if(regla.getOp().equals("gt")){
						consulta.append( " > ");
					} else if(regla.getOp().equals("bw")){
						consulta.append( " LIKE ");
					} else if(regla.getOp().equals("bn")){
						consulta.append( " NOT LIKE ");
					} else if(regla.getOp().equals("ew")){
						consulta.append( " LIKE ");
					} else if(regla.getOp().equals("en")){
						consulta.append( " NOT LIKE ");
					} else if(regla.getOp().equals("cn")){
						consulta.append( " LIKE ");
					} else if(regla.getOp().equals("nc")){
						consulta.append( " NOT LIKE ");
					} else if(regla.getOp().equals("in")){
						consulta.append( " IN(");
					} else if(regla.getOp().equals("ni")){
						consulta.append( " NOT IN(");
					} else if(regla.getOp().equals("gt")){
						consulta.append( " > ");
					}
					
					if(isPrimitivo){
						if(regla.getOp().equals("in")){
							consulta.append(regla.getData() + ") ");					
						} else if(regla.getOp().equals("ni")){
							consulta.append(regla.getData() + ") ");					
						} else {
							consulta.append(regla.getData());
						}
					} else {
						if(regla.getOp().equals("bw")){
							consulta.append("'" + regla.getData() + "%'");					
						} else if(regla.getOp().equals("bn")){
							consulta.append("'" + regla.getData() + "%'");					
						} else if(regla.getOp().equals("ew")){
							consulta.append("'%" + regla.getData() + "'");					
						} else if(regla.getOp().equals("en")){
							consulta.append("'%" + regla.getData() + "'");					
						} else if(regla.getOp().equals("cn")){
							consulta.append("'%" + regla.getData() + "%'");					
						} else if(regla.getOp().equals("nc")){
							consulta.append("'%" + regla.getData() + "%'");					
						} else if(regla.getOp().equals("in")){
							consulta.append("'" + regla.getData().replaceAll(", ", "','").replaceAll(",", "','").replaceAll("''", "'") + "') ");					
						} else if(regla.getOp().equals("ni")){
							consulta.append("'" + regla.getData().replaceAll(", ", "','").replaceAll(",", "','").replaceAll("''", "'") + "') ");					
						} else {
							if(isFecha){
								consulta.append("'" + regla.getData() + "'");								
							} else {
								consulta.append("'" + regla.getData() + "'");
							}
						}
	
					}					
					
				}
			}
		}
		
		if(busqueda.getSidx() != null && !busqueda.getSidx().isEmpty()){
			consulta.append(" ORDER BY c.").append(busqueda.getSidx()).append(" ").append(busqueda.getSord());
		}
		
		return consulta.toString();
	}
	
	
	private void leerValor(@SuppressWarnings("rawtypes") Class clase, Map<String, Object> valores, String campo, StringBuilder atributos) throws Exception{
		try{
		
			int index = campo.indexOf('.');
		
			boolean isPrimitive = false; 
			if(index > 0){
				String atributoRelacion = campo.substring(0, index);
				@SuppressWarnings("rawtypes")
				Class relacionClass = clase.getDeclaredField(atributoRelacion).getType();
				Object relacionObject = valores.get(atributoRelacion);
				
				if(relacionObject != null){
					@SuppressWarnings("unchecked")
					Map<String, Object> relacionValores = BeanUtilsBean.getInstance().getPropertyUtils().describe(relacionObject);
					
					campo = campo.substring(index + 1);
					
					leerValor(relacionClass, relacionValores, campo, atributos);
				}
				return;
			} else {
				isPrimitive = clase.getDeclaredField(campo).getType().isPrimitive();
			}
			
			if(isPrimitive){
				atributos.append(valores.get(campo)).append(",");
			} else {
				Object valor = valores.get(campo);
				if(valor == null){
					valor = "";
				}
				atributos.append("\"").append(valor).append("\",");
			}
		} catch(NoSuchFieldException e){
			if(clase.getSuperclass() != null){
				leerValor(clase.getSuperclass(), valores, campo, atributos);
			} else {
				return;
			}
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private String serializar(Class clase, List listado, String[] campos, Long paginas, int pagina, Long cantidad){
		StringBuilder rows = new StringBuilder("[");
		
		
		JSONSerializer serializer = new JSONSerializer().exclude("class", "version");
		for(Object entidad : listado){

			try{
				@SuppressWarnings("unchecked")
				Map<String, Object> valores = BeanUtilsBean.getInstance().getPropertyUtils().describe(entidad);
				
				rows.append("{");
				rows.append("\"id\":\"").append(valores.get("id")).append("\",");
				rows.append("\"cell\":[");
			
				StringBuilder arreglo = new StringBuilder();
				for(String campo: campos){
					leerValor(clase, valores, campo, arreglo);
				}


				String valor = arreglo.toString();
				valor = valor.substring(0, valor.length() -1);
				rows.append(valor);
			}catch(Exception e){
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			rows.append("]},");
		
		}

		
		
		String row = rows.toString();
		
		if(!listado.isEmpty()){
			row = row.substring(0, row.length() -1);
		}
		
		return "{\"total\": " + paginas + ", \"page\": " + pagina + ", \"records\": " + cantidad + ", \"rows\": " + row + "]}";
	}
	
	@SuppressWarnings("rawtypes")
	public String buscar(Class clase, EntityManager em, Busqueda busqueda, String[] campos){

		//Armamos dinamicamente la consulta
		String consulta = armarConsulta(clase, em, busqueda);
		
		//Generamos JPQL para las entidades y otro para obtener la cantidad resultados. 
		Query query = em.createQuery(consulta.toString());
		Query queryTotal = em.createQuery(consulta.toString().replaceAll("SELECT c", "SELECT COUNT(c)"));
		
		//Calculamos la cantidad de paginas de acuerdo a lo configurado
		Long total = (Long)queryTotal.getSingleResult();
		Long paginas = paginar(total, query, busqueda.getPage(), busqueda.getRows());
		
		//Serializar el resultado
		List listado = query.getResultList();
		String resultado = serializar(clase, listado, campos, paginas, busqueda.getPage(), total);
		return resultado;
	}
	
	@SuppressWarnings("rawtypes")
	public String combo(Class clase, EntityManager em, String id, String label){
		String consulta = "SELECT c FROM " + clase.getSimpleName() + " c WHERE c.fechaBaja IS NULL";
		return combo(clase, em, id, label, consulta);
	}
	
	@SuppressWarnings("rawtypes")
	public String combo(Class clase, EntityManager em, String id, String label, String consulta){
		return combo(clase, em, id, label, consulta, null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String combo(Class clase, EntityManager em, String id, String label, String consulta, String opcion){
		
		List<Object> resultado = em.createQuery(consulta).getResultList();
		
		Class claseId = clase;
		Class claseLabel = clase;
		
		if(id.startsWith("super.")){
			claseId = clase.getSuperclass();
			id = id.replaceAll("super.", "");
		} 
		

		if(label.startsWith("super.")){
			claseLabel = clase.getSuperclass();
			label = label.replaceAll("super.", "");
		} 
		
    	
		try{
			Method fieldId = claseId.getDeclaredMethod(id);
			Method fieldLabel = claseLabel.getDeclaredMethod(label);
				
	    	StringBuilder json = new StringBuilder(); 
	    	
	    	json.append("[");
	    	for(Object objeto : resultado){
	    		json.append("{");
	    		Long idValue = (Long)fieldId.invoke(objeto);
	    		String labelValue = (String)fieldLabel.invoke(objeto);
	    		
	    		json.append("\"id\":\"").append(idValue).append("\",\"value\":\"").append(labelValue).append("\"},");
	    	}
	    	
	    	
	    	if(!resultado.isEmpty()){
	    		String jsonParcial = json.toString().substring(0, json.toString().length() - 1); 
	    		if(opcion != null){
	    			jsonParcial = jsonParcial  + "," + opcion;
	    		}
	    		return  jsonParcial + "]";
	    	} else {
	    		if(opcion != null){
	    			return "[" + opcion + "]";
	    		} else {
	    			return "[]";
	    		}
	    	}
	    	
	    	
		} catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return JSONUtil.getErrorJSON(e.getMessage());
		}
	}
	
	
	
	
	public boolean isIDNull(String id){
		return !(id != null && !id.isEmpty() && !id.equals("0") && !id.equals("_empty"));
	}
	
	public boolean isCrear(String oper){
		return oper != null && oper.equals("add");
	}
	
	public boolean isEditar(String oper){
		return oper != null && oper.equals("edit");
	}
	
	public boolean isEliminar(String oper){
		return oper != null && oper.equals("del");
	}
	
	
	public Long paginar(Long total, Query query, int pagina, int cantidad){
		query.setMaxResults(cantidad);
		
		if(pagina > 0){
	        pagina = ((pagina - 1) * cantidad);
	    }
		
		query.setFirstResult(pagina);

		
		Long resto = 0L;
		Long pages = 0L;
		if(cantidad > 0){
			resto = total.longValue() % cantidad;
			pages = total.longValue() / cantidad;
		}
		
		if(resto.longValue() > 0){
			pages = pages + 1;
		}
		
		return pages;
	}
	
}
