# jmail

这是一款简洁的邮件发送库，非常小只有5个java文件，支持链式操作。

> 暂时没有发布到maven仓库，有一些细节未处理. 

## 特性

－ 简洁的邮件发送API
- 支持扩展邮件Message
－ 支持抄送／HTML／附件
－ 支持异步发送
－ 支持邮件模板
－ 可能是代码量最小的库了😂 非常好维护


## 来个栗子🌰

```java
try {
	MailSender mailSender = new MailSenderImpl();
	MailMessage mailMessage = new MailMessage();

	mailMessage
	.subject("hi，您有一封注册邮件！！！")
	.from("jelly_8090@163.com")
	.content("<p>hello</p><a href='http://www.baidu.com'>world</a>")
	.addTo("921293209@qq.com")
	.addFile("/Users/Anne/Documents/3849072.jpg", "/Users/Anne/Documents/vps.md");
	
	mailSender.debug(true).host("smtp.163.com").username("jelly_8090@163.com").password("###");
	mailSender.send(mailMessage);
} catch (MessagingException e) {
	e.printStackTrace();
}
```

## 问题建议

－ 联系我的邮箱：biezhi.me@gmail.com
－ 我的博客：https://biezhi.me
