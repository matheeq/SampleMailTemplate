package com.FileOperations.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.FileOperations.model.DTO;
import com.FileOperations.model.MailMessage;

public class PrepareAndSendEmail {
	private static final Logger LOGGER = LoggerFactory.getLogger(PrepareAndSendEmail.class);
	
	public static String emailIds;
	
	public static void sendUsersMails(DTO dto, List<String> myMailList) {

		LOGGER.info("sendUsersMails Start");
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {

				Map<String, Object> emailMap = new HashMap<String, Object>();
				MailMessage mailMessage = MailMessage.getInstance();
				mailMessage.setFrom(" test@gmail.com");
//				String[] nameList = dto.getEmail().split("@");
//				String name = " ";
//				if (nameList.length > 0) {
//					name = nameList[0];
//				}
				String subject= "Test Subject";
				String text = "Please find the details as below:";
				mailMessage.setSubject(subject);
				mailMessage.setTo(myMailList.toArray(new String[myMailList.size()]));
				mailMessage.setTemplateName("generic_template_new.flth");
				Map<String, Object> mapParams = new HashMap<String, Object>();
				emailMap.put("name", "Team");
				emailMap.put("text", text);
//				emailMap.put("email", dto.getEmail());
//				emailMap.put("pageName", dto.getPageName());
//				emailMap.put("ipAddress", dto.getIpAddress());
				emailMap.put("url", "test");
//				emailMap.put("userEmail", dto.getEmail());
				mapParams.putAll(emailMap);
				mailMessage.setMapParams(mapParams);
				LOGGER.info("Sending mail to " + StringUtils.arrayToCommaDelimitedString(mailMessage.getTo()));
				MailUtil.send(mailMessage);
			}
		});
		executorService.shutdown();
		LOGGER.info("sendUsersMails End");
	
	}


}
