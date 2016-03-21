package com.junicorn.jmail;

import java.util.Properties;

import javax.mail.Message;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.MimeMessage;  
 
/**  
 * 邮件发送程序  
 * @author haolloyin  
 */ 
public class MessageSender {  
 
    /**  
     * 创建Session对象，此时需要配置传输的协议，是否身份认证  
     */ 
    public Session createSession(String protocol) {  
        Properties property = new Properties();  
        property.setProperty("mail.transport.protocol", protocol);  
        property.setProperty("mail.smtp.auth", "true");  
 
        Session session = Session.getInstance(property);  
          
        // 启动JavaMail调试功能，可以返回与SMTP服务器交互的命令信息  
        // 可以从控制台中看一下服务器的响应信息  
        session.setDebug(true);   
        return session;  
    }  
 
    /**  
     * 传入Session、MimeMessage对象，创建 Transport 对象发送邮件  
     */ 
    public void sendMail(Session session, MimeMessage msg) throws Exception {  
          
        // 设置发件人使用的SMTP服务器、用户名、密码  
        String smtpServer = "smtp.qq.com";  
        String user = "921293209@qq.com";  
        String pwd = "hitvidzrpawabeab";  
 
        // 由 Session 对象获得 Transport 对象  
        Transport transport = session.getTransport();  
        // 发送用户名、密码连接到指定的 smtp 服务器  
        transport.connect(smtpServer, user, pwd);  
 
        transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));  
        transport.close();  
    }  
 
    // 测试：发送邮件  
    public static void main(String[] args) throws Exception {  
        MessageSender sender = new MessageSender();  
          
        // 指定使用SMTP协议来创建Session对象  
        Session session = sender.createSession("smtp");  
        // 使用前面文章所完成的邮件创建类获得 Message 对象  
        MimeMessage mail = new WithAttachmentMessage().createMessage(session);  
        sender.sendMail(session, mail);  
    }  
} 