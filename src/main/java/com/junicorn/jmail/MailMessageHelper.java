package com.junicorn.jmail;

import java.io.File;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.junicorn.jmail.exception.MailException;

public class MailMessageHelper {
	
	public static void copy(MimeMessage mimeMessage, MailMessage mailMessage) throws MessagingException {
		
		//设置发件人
		mimeMessage.setFrom(new InternetAddress(mailMessage.from()));
        
		//设置收件人
		List<String> to = mailMessage.toList();
		if(null == to || to.size() == 0){
			throw new MailException("to mail not null.");
		}
		
		InternetAddress[] addresses = new InternetAddress[to.size()];
		for(int i=0, len=to.size(); i<len; i++){
			addresses[i] = new InternetAddress(to.get(i));
		}
        mimeMessage.setRecipients(Message.RecipientType.TO , addresses);
        
        // 设置抄送人
        List<String> cc = mailMessage.ccList();
		if(null != cc && !cc.isEmpty()){
			InternetAddress[] ccAddresses = new InternetAddress[cc.size()];
			for(int i=0, len=cc.size(); i<len; i++){
				ccAddresses[i] = new InternetAddress(cc.get(i));
			}
			mimeMessage.setRecipients(Message.RecipientType.CC , ccAddresses);
		}
		
        //设置邮件主题
        mimeMessage.setSubject(mailMessage.subject());
        
        //设置发送日期
        mimeMessage.setSentDate(mailMessage.date());
        
        //构造Multipart
        MimeMultipart allPart = new MimeMultipart("mixed");  
        
        // 文本内容
        if(!mailMessage.isHtml()){
        	mimeMessage.setText(mailMessage.content());
        } else {
        	MimeBodyPart content = createContent(mailMessage.content());
            allPart.addBodyPart(content);  
		}
        
        // 附件
        List<File> attachs = mailMessage.attachs();
        if(null != attachs && !attachs.isEmpty()){
        	for(File file : attachs){
        		MimeBodyPart attach = createAttachment(file);
        		allPart.addBodyPart(attach);
        	}
        }
        
        mimeMessage.setContent(allPart);
        mimeMessage.saveChanges();
	}
	
	/**  
     * 根据传入的文件路径创建附件并返回  
     */ 
    public static MimeBodyPart createAttachment(File file) throws MessagingException {  
        MimeBodyPart attachmentPart = new MimeBodyPart();  
        FileDataSource fds = new FileDataSource(file);  
        attachmentPart.setDataHandler(new DataHandler(fds));  
        attachmentPart.setFileName(fds.getName());  
        return attachmentPart;  
    }  
    
    public static MimeBodyPart createContent(String body) throws MessagingException {  
    	// 用于保存最终正文部分  
        MimeBodyPart contentBody = new MimeBodyPart();  
        // 用于组合文本和图片，"related"型的MimeMultipart对象  
        MimeMultipart contentMulti = new MimeMultipart("related");  
 
        // 正文的文本部分
        MimeBodyPart textBody = new MimeBodyPart();  
        textBody.setContent(body, "text/html; charset=utf-8");  
        contentMulti.addBodyPart(textBody);
        
        // 将上面"related"型的 MimeMultipart 对象作为邮件的正文  
        contentBody.setContent(contentMulti);  
        
        return contentBody;  
    } 
    
}
