package com.FileOperations.util;

import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import com.FileOperations.model.MailMessage;

@Component
public class MailUtil {
	private static ServletContext servletContext;

	private static JavaMailSender javaMailSender;

	private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

	private static SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");

	@Autowired(required = true)
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		MailUtil.javaMailSender = javaMailSender;
	}

	
	@Autowired(required = true)
	public void setConfiguration(ServletContext servletContext) {
		MailUtil.servletContext = servletContext;
	}
	  
	 
	public static void send(final MailMessage mailMessage) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Sending mail to " + StringUtils.arrayToCommaDelimitedString(mailMessage.getTo()));
			LOGGER.debug("Sending mail cc " + StringUtils.arrayToCommaDelimitedString(mailMessage.getCc()));
		}
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom(mailMessage.getFrom());
				message.setTo(mailMessage.getTo());
				if (mailMessage.getCc() != null) {
					message.setCc(mailMessage.getCc());
				}
				message.setSubject(mailMessage.getSubject());
				String bodyText = FreemarkerUtils.processTemplateIntoString(mailMessage.getTemplateName(),
						mailMessage.getMapParams());
				message.setText(bodyText, true);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("\n" + bodyText);
				}
				InputStream is = null;
				try {
					/*
					 * is = servletContext.getResourceAsStream("/logo-email.png");
					 * ByteArrayDataSource source = new ByteArrayDataSource(is, "image/png");
					 * message.addInline("logo.png", source);
					 */
				} catch (Exception e) {
					LOGGER.error("Error: " + e.getMessage(), e);
				} finally {
					try {
						if (is != null)
							is.close();
					} catch (Exception ef) {
					}
				}
			}
		};
		javaMailSender.send(preparator);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Sending mail takes " + stopWatch.getTotalTimeMillis() + " ms");
		}
	}
}
