package com.wbyweb.bolg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootBolgApplicationTests {

    @Test
    public void contextLoads() {

    }
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送包含简单文本的邮件
     */
    @Test
    public void sendTxtMail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 设置收件人，寄件人
//        simpleMailMessage.setTo(new String[] {"18210493942@163.com"});
//        simpleMailMessage.setFrom("weibiaoyi@126.com");
//        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
//        simpleMailMessage.setText("这里是一段简单文本。");
//        // 发送邮件
//        mailSender.send(simpleMailMessage);
//
//        System.out.println("邮件已发送");
    }

}
