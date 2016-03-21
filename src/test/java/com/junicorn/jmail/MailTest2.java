package com.junicorn.jmail;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import com.jmail.MailMessage;
import com.jmail.MailSender;
import com.jmail.MailSenderImpl;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

public class MailTest2 {

	public static void main(String[] args) {
		MailSender mailSender = new MailSenderImpl();
		
		MailMessage mailMessage = new MailMessage();
		
		try {
			
			PebbleEngine engine = new PebbleEngine.Builder().build();
			PebbleTemplate compiledTemplate = engine.getTemplate("register.html");

			Map<String, Object> context = new HashMap<String, Object>();
			context.put("username", "biezhi");
			context.put("email", "admin@java-china.org");

			Writer writer = new StringWriter();
			compiledTemplate.evaluate(writer, context);

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
		} catch (PebbleException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
