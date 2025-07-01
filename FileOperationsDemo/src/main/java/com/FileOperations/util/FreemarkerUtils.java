package com.FileOperations.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Component
public class FreemarkerUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerUtils.class);

	private static Configuration _configuration;

	@Autowired(required = true)
	public void setConfiguration(Configuration _configuration) {
		FreemarkerUtils._configuration = _configuration;
	}

	public static String processTemplateIntoString(String template,
			Map<String, Object> mapParams) {
		String text = "";
		try {
			text = FreeMarkerTemplateUtils.processTemplateIntoString(
					_configuration.getTemplate(template), mapParams);
		} catch (Exception e) {
			LOGGER.error("Error: " + e.getMessage(), e);
		}
		return text;
	}


}
