# oh-my-email

或许是最小的 Java 邮件发送类库了。

[![Build Status](https://img.shields.io/travis/biezhi/oh-my-email.svg?style=flat-square)](https://travis-ci.org/biezhi/oh-my-email)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2ef611f3fa044c8f8d8fc31cf0acd8a7)](https://www.codacy.com/app/biezhi/oh-my-email?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=biezhi/oh-my-email&amp;utm_campaign=Badge_Grade)
[![codecov.io](https://img.shields.io/codecov/c/github/biezhi/oh-my-email/master.svg?style=flat-square)](http://codecov.io/github/biezhi/oh-my-email?branch=master)
[![maven-central](https://img.shields.io/maven-central/v/io.github.biezhi/oh-my-email.svg?style=flat-square)](http://search.maven.org/#search%7Cga%7C1%7Coh-my-email)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Twitter URL](https://img.shields.io/twitter/url/https/twitter.com/biezhii.svg?style=social&label=Follow%20Twitter)](https://twitter.com/biezhii)

## 特性

- 简洁的邮件发送API
- 支持自定义发件人昵称
- 支持扩展邮件Message
- 支持抄送／HTML／附件
- 支持异步发送
- 支持邮件模板
- 可能是代码量最小的库了，200多行 😂 非常好维护

## 使用

**maven坐标**

```xml
<dependency>
    <groupId>io.github.biezhi</groupId>
    <artifactId>oh-my-email</artifactId>
    <version>0.0.4</version>
</dependency>
```

## 举个栗子🌰

```java
@Before
public void before() throws GeneralSecurityException {
    // 配置，一次即可
    OhMyEmail.config(SMTP_QQ(), "xiaojiejie@qq.com", "your@password");
}

@Test
public void testSendText() throws MessagingException {
    OhMyEmail.subject("这是一封测试TEXT邮件")
            .from("小姐姐的邮箱")
            .to("xiaojiejie@gmail.com")
            .text("信件内容")
            .send();
}

@Test
public void testSendHtml() throws MessagingException {
    OhMyEmail.subject("这是一封测试HTML邮件")
            .from("小姐姐的邮箱")
            .to("xiaojiejie@gmail.com")
            .html("<h1 font=red>信件内容</h1>")
            .send();
}

@Test
public void testSendAttach() throws MessagingException {
    OhMyEmail.subject("这是一封测试附件邮件")
            .from("小姐姐的邮箱")
            .to("xiaojiejie@gmail.com")
            .html("<h1 font=red>信件内容</h1>")
            .attach(new File("/Users/biezhi/Downloads/hello.jpeg"), "测试图片.jpeg")
            .send();
}

@Test
public void testSendAttachURL() throws MessagingException {
    try {
        OhMyEmail.subject("这是一封测试网络资源作为附件的邮件")
                .from("小姐姐的邮箱")
                .to("xiaojiejie@gmail.com")
                .html("<h1 font=red>信件内容</h1>")
                .attachURL(new URL("https://avatars1.githubusercontent.com/u/2784452?s=40&v=4"), "测试图片.jpeg")
                .send();
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
}


@Test
public void testPebble() throws IOException, PebbleException, MessagingException {
    PebbleEngine engine = new PebbleEngine.Builder().build();
    PebbleTemplate compiledTemplate = engine.getTemplate("register.html");

    Map<String, Object> context = new HashMap<String, Object>();
    context.put("username", "biezhi");
    context.put("email", "admin@biezhi.me");

    Writer writer = new StringWriter();
    compiledTemplate.evaluate(writer, context);

    String output = writer.toString();
    System.out.println(output);

    OhMyEmail.subject("这是一封测试Pebble模板邮件")
            .from("小姐姐的邮箱")
            .to("xiaojiejie@gmail.com")
            .html(output)
            .send();
}

@Test
public void testJetx() throws IOException, PebbleException, MessagingException {
    JetEngine engine = JetEngine.create();
    JetTemplate template = engine.getTemplate("/register.jetx");

    Map<String, Object> context = new HashMap<String, Object>();
    context.put("username", "biezhi");
    context.put("email", "admin@biezhi.me");
    context.put("url", "<a href='http://biezhi.me'>https://biezhi.me/active/asdkjajdasjdkaweoi</a>");

    StringWriter writer = new StringWriter();
    template.render(context, writer);
    String output = writer.toString();
    System.out.println(output);

    OhMyEmail.subject("这是一封测试Jetx模板邮件")
            .from("小姐姐的邮箱")
            .to("xiaojiejie@gmail.com")
            .html(output)
            .send();
}
```

### 邮件模版

```html
<div>
    <p>亲爱的<b>{{ username }}</b>, 欢迎加入 biezhi !</p>
    <p>当您收到这封信的时候，您已经可以正常登录了。</p>
    <p>请点击链接登录首页: <a href='http://www.baidu.com'>http://biezhi.me/xxxxx</a></p>
    <p>如果您的 email 程序不支持链接点击，请将上面的地址拷贝至您的浏览器(如IE)的地址栏进入。</p>
    <p>如果您还想申请管理员权限，可以联系管理员 {{ email }}</p>
    <p>我们对您产生的不便，深表歉意。</p>
    <p>希望您在 biezhi 系统度过快乐的时光!</p>
    <p></p>
    <p>-----------------------</p>
    <p></p>
    <p>(这是一封自动产生的email，请勿回复。)</p>
</div>
```

## 问题建议

- 我的邮箱：`biezhi.me#gmail.com`
