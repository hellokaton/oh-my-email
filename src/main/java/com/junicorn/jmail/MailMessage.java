package com.junicorn.jmail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MailMessage {

	private String from;
	
	// 收件人列表
	private List<String> toList;
	
	// 抄送列表
	private List<String> ccList;
	
	// 附件列表
	private List<File> attachs;
	
	private boolean isHtml = false;
	
	private String content;
	
	private String subject;
	
	private Date date;
	
	public MailMessage() {
		this.toList = new ArrayList<String>();
		this.ccList = new ArrayList<String>();
		this.attachs = new ArrayList<File>();
		this.date = new Date();
	}
	
	public MailMessage from(String from) {
		this.from = from;
		return this;
	}
	
	public MailMessage addTo(String to) {
		this.toList.add(to);
		return this;
	}
	
	public MailMessage addTo(List<String> toList) {
		this.toList.addAll(toList);
		return this;
	}
	
	public MailMessage addTo(String... to) {
		this.toList.addAll(Arrays.asList(to));
		return this;
	}

	public MailMessage addCC(String cc) {
		this.ccList.add(cc);
		return this;
	}
	
	public MailMessage addCC(List<String> ccList) {
		this.ccList.addAll(ccList);
		return this;
	}
	
	public MailMessage addCC(String... cc) {
		this.ccList.addAll(Arrays.asList(cc));
		return this;
	}

	public MailMessage subject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public MailMessage date(Date date) {
		this.date = date;
		return this;
	}

	public MailMessage content(String content) {
		this.content = content;
		return this;
	}
	
	public MailMessage content(String content, boolean isHtml) {
		this.content = content;
		this.isHtml = isHtml;
		return this;
	}
	
	public MailMessage addFile(String filePath) {
		File file = new File(filePath);
		return addFile(file);
	}
	
	public MailMessage addFile(String... filePaths) {
		for(String filePath : filePaths){
			addFile(filePath);
		}
		return this;
	}
	
	public MailMessage addFile(File... files) {
		for(File file : files){
			addFile(file);
		}
		return this;
	}
	
	public MailMessage addFile(File file) {
		this.attachs.add(file);
		return this;
	}

	public String from() {
		return from;
	}
	
	public String content() {
		return content;
	}

	public String subject() {
		return subject;
	}

	public Date date() {
		return date;
	}
	
	public boolean isHtml() {
		return isHtml;
	}

	public List<String> toList() {
		return toList;
	}

	public List<String> ccList() {
		return ccList;
	}

	public List<File> attachs() {
		return attachs;
	}

	
}
