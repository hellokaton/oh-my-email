package com.jmail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.jmail.exception.MailException;

import blade.kit.Assert;

public class MailMessage {

	// 发件人邮箱
	private String from;
	
	// 发件人昵称（可选）
	private String nickName;
	
	// 收件人列表
	private List<String> toList;
	
	// 抄送列表
	private List<String> ccList;
	
	// 附件列表
	private List<File> attachs;
	
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
		Assert.notEmpty(from, "from mail not is empty!");
		this.from = from;
		return this;
	}
	
	public MailMessage from(String nickName, String from) {
		Assert.notEmpty(nickName, "nick name not is empty!");
		Assert.notEmpty(from, "from mail not is empty!");
		this.nickName = nickName;
		this.from = from;
		return this;
	}
	
	public MailMessage addTo(String to) {
		Assert.notEmpty(to, "to mail not is empty!");
		this.toList.add(to);
		return this;
	}
	
	public MailMessage addTo(List<String> toList) {
		Assert.notEmpty(toList, "toList mail not is empty!");
		this.toList.addAll(toList);
		return this;
	}
	
	public MailMessage addTo(String... to) {
		Assert.notEmpty(to, "to mail array not is empty!");
		this.toList.addAll(Arrays.asList(to));
		return this;
	}

	public MailMessage addCC(String cc) {
		Assert.notEmpty(cc, "cc mail not is empty!");
		this.ccList.add(cc);
		return this;
	}
	
	public MailMessage addCC(List<String> ccList) {
		Assert.notEmpty(ccList, "ccList mail not is empty!");
		this.ccList.addAll(ccList);
		return this;
	}
	
	public MailMessage addCC(String... cc) {
		Assert.notEmpty(cc, "cc mail array not is empty!");
		this.ccList.addAll(Arrays.asList(cc));
		return this;
	}

	public MailMessage subject(String subject) {
		Assert.notEmpty(subject, "subject mail not is empty!");
		this.subject = subject;
		return this;
	}
	
	public MailMessage date(Date date) {
		Assert.notNull(date, "date not is empty!");
		this.date = date;
		return this;
	}

	public MailMessage content(String content) {
		Assert.notEmpty(content, "mail content not is empty!");
		this.content = content;
		return this;
	}
	
	public MailMessage addFile(String filePath) {
		Assert.notEmpty(filePath, "filePath not is empty!");
		File file = new File(filePath);
		if(null == file || !file.exists()){
			throw new MailException("the file not exists!");
		}
		return addFile(file);
	}
	
	public MailMessage addFile(String... filePaths) {
		Assert.notEmpty(filePaths, "filePaths not is empty!");
		for(String filePath : filePaths){
			addFile(filePath);
		}
		return this;
	}
	
	public MailMessage addFile(File... files) {
		Assert.notEmpty(files, "files not is empty!");
		for(File file : files){
			addFile(file);
		}
		return this;
	}
	
	public MailMessage addFile(File file) {
		if(null == file || !file.exists()){
			throw new MailException("the file not exists!");
		}
		this.attachs.add(file);
		return this;
	}

	public String from() {
		return from;
	}
	
	public String nickName() {
		return nickName;
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
