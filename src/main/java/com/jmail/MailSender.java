package com.jmail;

import javax.mail.MessagingException;

public interface MailSender {

	void send(MailMessage mailMessage) throws MessagingException;
	
	void send(MailMessage mailMessage, boolean isAsync) throws MessagingException;
	
	void send(MailMessage... mailMessages) throws MessagingException;
	
	MailSender host(String host);
	
	MailSender username(String username);
	
	MailSender password(String password);
	
	MailSender auth(boolean auth);
	
	MailSender debug(boolean debug);
	
	MailSender protocol(String protocol);
	
	MailSender load(String mailProperties);
	
}
