package com.core.irrigacion.util;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;


@RooJson
@RooJavaBean
public class Resultado {
	private Long paginas; 
	private Long pagina; 
	private List<Celda> keys;
}
