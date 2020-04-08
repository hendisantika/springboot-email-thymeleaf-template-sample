package com.hendisantika.springbootemailthymeleaftemplatesample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-email-thymeleaf-template-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/04/20
 * Time: 03.03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String from;
    private String mailTo;
    private String subject;
    private List<Object> attachments;
    private Map<String, Object> props;
}
