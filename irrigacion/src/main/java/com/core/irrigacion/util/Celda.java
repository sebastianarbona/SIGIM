package com.core.irrigacion.util;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

@RooJson
@RooJavaBean
public class Celda {
	private String key;
	private Object[] cells;
}
