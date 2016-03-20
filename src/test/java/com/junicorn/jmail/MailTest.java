package com.junicorn.jmail;

import javax.mail.MessagingException;

public class MailTest {

	public static void main(String[] args) {
		MailSender mailSender = new MailSenderImpl();
		
		MailMessage mailMessage = new MailMessage();
		
		try {
			mailMessage
			.subject("hi，您有一封注册邮件！！！")
			.from("921293209@qq.com")
			.content("<p>hello</p><a href='http://www.baidu.com'>world</a>")
			.addTo("921293209@qq.com")
			.addFile("/Users/Anne/Documents/3849072.jpg", "/Users/Anne/Documents/vps.md");
			
			mailSender.debug(true).host("smtp.qq.com").username("921293209@qq.com").password("");
			
			mailSender.send(mailMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
