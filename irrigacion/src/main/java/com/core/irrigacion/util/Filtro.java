package com.core.irrigacion.util;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

@RooJson
@RooJavaBean
public class Filtro {
	private String groupOp;
	private List<Regla> rules;
}
