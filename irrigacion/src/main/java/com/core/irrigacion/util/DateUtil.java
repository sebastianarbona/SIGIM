package com.core.irrigacion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	
	public static String format(Date fecha){
		if(fecha != null) {
			return sdf.format(fecha);
		} else {
			return null;
		}
	}
	
	public static Date parseDate(String fecha)
	{
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaDate = null;
	    try {
	        fechaDate = formato.parse(fecha);
	    }
	    catch (ParseException ex){
	        System.out.println(ex);
	    }
	    return fechaDate;
	}
}
