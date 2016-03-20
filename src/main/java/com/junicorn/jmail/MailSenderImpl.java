package com.junicorn.jmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class MailSenderImpl implements MailSender {
	
	private String host;
	
	private String username;
	
	private String password;
	
	private Properties props;
	
	private boolean debug;
	
	public MailSenderImpl() {
		this(false);
	}
	
	public MailSenderImpl(boolean debug) {
		this.debug = debug;
		this.props = new Properties();
		this.props.setProperty("mail.transport.protocol", "smtp");
		this.props.setProperty("mail.smtp.auth", "true");
		this.props.setProperty("mail.smtp.starttls.enable", "true");
	}
	
	@Override
	public MailSender protocol(String protocol) {
		this.props.setProperty("mail.transport.protocol", protocol);  
		return this;
	}
	
	@Override
	public void send(MailMessage mailMessage) throws MessagingException {
		
		Session session = Session.getInstance(props);
		
		// 启动JavaMail调试功能，可以返回与SMTP服务器交互的命令信息  
        // 可以从控制台中看一下服务器的响应信息  
        session.setDebug(debug);
        
		MimeMessage mimeMessage = new MimeMessage(session);
		MailMessageHelper.copy(mimeMessage, mailMessage);
		
		System.out.println("send mail ...");
	    
		// 由 Session 对象获得 Transport 对象  
        Transport transport = session.getTransport();
        // 发送用户名、密码连接到指定的 smtp 服务器  
        transport.connect(host, username, password);  
 
        transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));  
        transport.close();
        
		System.out.println("send success!");
	}
	
	@Override
	public void send(MailMessage... mailMessages) throws MessagingException {
		for(MailMessage mailMessage : mailMessages){
			send(mailMessage);
		}
	}

	@Override
	public MailSender username(String username) {
		this.username = username;
		return this;
	}

	@Override
	public MailSender password(String password) {
		this.password = password;
		return this;
	}
	
	@Override
	public MailSender auth(boolean auth) {
		this.props.setProperty("mail.smtp.auth", auth+""); 
		return this;
	}
	
	@Override
	public MailSender debug(boolean debug) {
		this.debug = debug;
		return this;
	}
	
	@Override
	public MailSender host(String host) {
		this.host = host;
		return this;
	}
	
	@Override
	public MailSender load(String mailProperties) {
		return this;
	}
	
}
