package com.hendisantika.springbootemailthymeleaftemplatesample;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Loads the full application context without talking to a real SMTP server.
 * The ApplicationRunner still renders the Thymeleaf template and builds the
 * MIME message, but the mocked {@link JavaMailSender} swallows the send.
 */
@SpringBootTest
class SpringbootEmailThymeleafTemplateSampleApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        verify(javaMailSender, atLeastOnce()).send(any(MimeMessage.class));
    }

    @TestConfiguration
    static class MockMailSenderConfig {

        @Bean
        @Primary
        JavaMailSender javaMailSender() {
            JavaMailSender sender = mock(JavaMailSender.class);
            when(sender.createMimeMessage())
                    .thenAnswer(invocation -> new MimeMessage(Session.getInstance(new Properties())));
            return sender;
        }
    }
}
