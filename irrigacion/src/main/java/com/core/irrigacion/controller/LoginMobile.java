package com.core.irrigacion.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.irrigacion.util.ABMController;
import com.core.irrigacion.util.JSONUtil;

@Controller
@RequestMapping("/login/")
public class LoginMobile extends ABMController{

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mobile", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String loginMobile(HttpServletResponse response, @RequestParam(required=false) String usuario, 
											@RequestParam(required=false) String password, @RequestParam(required=false) String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setCharacterEncoding("iso-8859-1");
		try {
			if(usuario.equals("usuario")){
				if(password.equals("1234")){
					return JSONUtil.getIdJSON("1");
				}else{
					return "error";
				}
			}else{
				return "error";
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println(JSONUtil.getErrorJSON(e.getMessage()));
			return JSONUtil.getErrorJSON(e.getMessage());
		}
		
	}	
}