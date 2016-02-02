package com.core.irrigacion.util;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

@RooJson
@RooJavaBean
public class Busqueda {
	private String _search;
	private String nd;
	private int rows;
	private int page; 
	private String sidx;
	private String sord;
	private String seachField;
	private String searchString;
	private String searchOper;
	private String filters;
}
