package com.junicorn.jmail;
import java.io.FileOutputStream;  
import java.util.Properties;  
 
import javax.activation.DataHandler;  
import javax.activation.FileDataSource;  
import javax.mail.Message;  
import javax.mail.Session;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;  
 
/**  
 * 创建内含附件、图文并茂的邮件  
 * @author haolloyin  
 */ 
public class WithAttachmentMessage {  
 
    /**  
     * 根据传入的文件路径创建附件并返回  
     */ 
    public MimeBodyPart createAttachment(String fileName) throws Exception {  
        MimeBodyPart attachmentPart = new MimeBodyPart();  
        FileDataSource fds = new FileDataSource(fileName);  
        attachmentPart.setDataHandler(new DataHandler(fds));  
        attachmentPart.setFileName(fds.getName());  
        return attachmentPart;  
    }  
 
    /**  
     * 根据传入的邮件正文body和文件路径创建图文并茂的正文部分  
     */ 
    public MimeBodyPart createContent(String body, String fileName)  
            throws Exception {  
        // 用于保存最终正文部分  
        MimeBodyPart contentBody = new MimeBodyPart();  
        // 用于组合文本和图片，"related"型的MimeMultipart对象  
        MimeMultipart contentMulti = new MimeMultipart("related");  
 
        // 正文的文本部分  
        MimeBodyPart textBody = new MimeBodyPart();  
        textBody.setContent(body, "text/html;charset=gbk");  
        contentMulti.addBodyPart(textBody);  
 
        // 正文的图片部分  
        MimeBodyPart jpgBody = new MimeBodyPart();  
        FileDataSource fds = new FileDataSource(fileName);  
        jpgBody.setDataHandler(new DataHandler(fds));  
        jpgBody.setContentID("logo_jpg");  
        contentMulti.addBodyPart(jpgBody);  
 
        // 将上面"related"型的 MimeMultipart 对象作为邮件的正文  
        contentBody.setContent(contentMulti);  
        return contentBody;  
    }  
 
    /**  
     * 根据传入的 Seesion 对象创建混合型的 MIME消息  
     */ 
    public MimeMessage createMessage(Session session) throws Exception {  
 
        String from = "921293209@qq.com";  
        String to = "921293209@qq.com";  
          
        String subject = "创建内含附件、图文并茂的邮件！";  
        String body = "<h4>内含附件、图文并茂的邮件测试！！！</h4> </br>" 
                + "<a href = http://haolloyin.blog.51cto.com/> 蚂蚁</a></br>" 
                + "<img src = \"cid:logo_jpg\"></a>";  
 
        MimeMessage msg = new MimeMessage(session);  
        msg.setFrom(new InternetAddress(from));  
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));  
        msg.setSubject(subject);  
 
        // 创建邮件的各个 MimeBodyPart 部分  
//        MimeBodyPart attachment01 = createAttachment("F:\\java\\Hello_JavaMail.java");  
//        MimeBodyPart attachment02 = createAttachment("F:\\java\\Hello_JavaMail.7z");  
        MimeBodyPart content = createContent(body, "/Users/Anne/Documents/3849072.jpg");  
 
        // 将邮件中各个部分组合到一个"mixed"型的 MimeMultipart 对象  
        MimeMultipart allPart = new MimeMultipart("mixed");  
//        allPart.addBodyPart(attachment01);  
//        allPart.addBodyPart(attachment02);  
        allPart.addBodyPart(content);  
 
        // 将上面混合型的 MimeMultipart 对象作为邮件内容并保存  
        msg.setContent(allPart);  
        msg.saveChanges();  
        return msg;  
    }  
 
    // 测试生成邮件  
    public static void main(String[] args) throws Exception {  
        WithAttachmentMessage mail = new WithAttachmentMessage();  
        Session session = Session.getDefaultInstance(new Properties());  
        MimeMessage message = mail.createMessage(session);  
        message.writeTo(new FileOutputStream("withAttachmentMail.eml"));  
    }  
}