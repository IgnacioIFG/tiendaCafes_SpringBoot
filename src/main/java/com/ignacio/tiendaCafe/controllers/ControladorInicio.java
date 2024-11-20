package com.ignacio.tiendaCafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ignacio.tiendaCafe.servicioSetUp.SetUp;

@Controller
public class ControladorInicio {

	@Autowired
	private SetUp setUp;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping()
	public String inicio() {
		setUp.setUp();
		// vemos idioma del usuario para devolverle el html correspondiente
		String idiomaActual = messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		if (idiomaActual.equals("en")) {
			return "index_en";
		}else if(idiomaActual.equals("fr")) {
			return "index_fr";
		}
		return "index";
	}

}
