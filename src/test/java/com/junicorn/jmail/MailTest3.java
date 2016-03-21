package com.junicorn.jmail;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;

public class MailTest3 {

	public static void main(String[] args) {
		MailSender mailSender = new MailSenderImpl();
		
		MailMessage mailMessage = new MailMessage();
		
		try {
			
	        JetEngine engine = JetEngine.create();

	        JetTemplate template = engine.getTemplate("/register.jetx");
	        
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("username", "biezhi");
			context.put("email", "admin@java-china.org");
			context.put("url", "<a href='http://java-china.org'>https://java-china.org/active/asdkjajdasjdkaweoi</a>");

			StringWriter writer = new StringWriter();
	        template.render(context, writer);
	        
			String output = writer.toString();
			
			System.out.println(output);
			
			mailMessage
			.subject("BladeJava 注册邮件")
			.from("jelly_8090@163.com")
			.content(output)
			.addTo("921293209@qq.com");
			
			mailSender.debug(true).host("smtp.163.com").username("jelly_8090@163.com").password("###");
			
			mailSender.send(mailMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
