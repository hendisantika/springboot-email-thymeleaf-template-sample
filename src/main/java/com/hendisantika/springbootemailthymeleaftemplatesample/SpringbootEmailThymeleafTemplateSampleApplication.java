package com.hendisantika.springbootemailthymeleaftemplatesample;

import com.hendisantika.springbootemailthymeleaftemplatesample.model.Mail;
import com.hendisantika.springbootemailthymeleaftemplatesample.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringbootEmailThymeleafTemplateSampleApplication implements ApplicationRunner {
    private static Logger log = LoggerFactory.getLogger(SpringbootEmailThymeleafTemplateSampleApplication.class);
    @Autowired
    private EmailSenderService emailService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEmailThymeleafTemplateSampleApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("START... Sending email");

        Mail mail = new Mail();
        mail.setFrom("yourmailid@email.com");//replace with your desired email
        mail.setMailTo("tomail@email.com");//replace with your desired email
        mail.setSubject("Email with Spring boot and thymeleaf template!");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Developer!");
        model.put("location", "United States");
        model.put("sign", "Java Developer");
        mail.setProps(model);

        emailService.sendEmail(mail);
        log.info("END... Email sent success");

    }
}
