package com.hendisantika.springbootemailthymeleaftemplatesample.service;

import com.hendisantika.springbootemailthymeleaftemplatesample.model.Mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-email-thymeleaf-template-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/04/20
 * Time: 03.05
 */
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender emailSender;

    private final SpringTemplateEngine templateEngine;

    public void sendEmail(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.addAttachment("ninja.jpeg", new ClassPathResource("images/ninja.jpeg"));

        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process("newsletter-template", context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
}
