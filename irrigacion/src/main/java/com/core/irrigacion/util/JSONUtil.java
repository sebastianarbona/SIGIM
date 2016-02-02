package com.core.irrigacion.util;
public final class JSONUtil {
	
	private JSONUtil() {
	}
	
	public static String getSimpleJSON(String[] propiedades, Object[] valores){
		
		StringBuilder sb = new StringBuilder("{");
		for(int n = 0; n < propiedades.length; n++){
			if(valores[n].getClass().isAssignableFrom(Number.class) || valores[n].getClass().isAssignableFrom(Double.class) || valores[n].getClass().isAssignableFrom(Integer.class)){
				sb.append("\"").append(propiedades[n]).append("\":").append(valores[n]);
			} else {
				sb.append("\"").append(propiedades[n]).append("\":\"").append(valores[n]).append("\"");				
			}
			

			if(n + 1 < propiedades.length){
				sb.append(",");
			}
		}
		
		return sb.append("}").toString();
	}
	
	public static String getSimpleJSON(String propiedad, Object valor){
		return "{\"" + propiedad + "\":\"" + valor +  "\"}";
	}
	
	public static String getIdJSON(String id){
		return getSimpleJSON("id", id);
	}
	
	public static String getErrorJSON(String error){
		return getSimpleJSON("error", error);
	}
	
	public static String getLinkJSON(String url){
		return getSimpleJSON("link", url);
	}
	
}
