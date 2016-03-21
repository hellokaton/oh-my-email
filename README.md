# jmail

这是一款简洁的邮件发送库，非常小只有5个java文件，支持链式操作。

> 暂时没有发布到maven仓库，有一些细节未处理. 

## 特性

- 简洁的邮件发送API
- 支持扩展邮件Message
- 支持抄送／HTML／附件
- 支持异步发送
- 支持邮件模板
- 可能是代码量最小的库了😂 非常好维护


## 举个栗子🌰

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

### 邮件模版

```html
<div>
	<p>亲爱的<b>{{ username }}</b>, 欢迎加入JavaChina!</p>
  	<p>当您收到这封信的时候，您已经可以正常登录了。</p>
  	<p>请点击链接登录首页: <a href='http://www.baidu.com'>http://java-china.org/xxxxx</a></p>
  	<p>如果您的email程序不支持链接点击，请将上面的地址拷贝至您的浏览器(如IE)的地址栏进入。</p>
  	<p>如果您还想申请管理员权限，可以联系管理员 {{ email }}</p>
  	<p>我们对您产生的不便，深表歉意。</p>
  	<p>希望您在JavaChina度过快乐的时光!</p>
  	<p></p>
  	<p>-----------------------</p>
  	<p></p>
  	<p>(这是一封自动产生的email，请勿回复。)</p>
</div>
```
```java
try {
	
	MailSender mailSender = new MailSenderImpl();
	MailMessage mailMessage = new MailMessage();

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
```
## 问题建议

- 联系我的邮箱：biezhi.me@gmail.com
- 我的博客：https://biezhi.me
