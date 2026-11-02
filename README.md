# springboot-email-thymeleaf-template-sample

[![Java CI with Maven](https://github.com/hendisantika/springboot-email-thymeleaf-template-sample/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/springboot-email-thymeleaf-template-sample/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9.11-C71A36?logo=apachemaven)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

In this repository we demonstrate how to send an HTML email with attachments and inline images using
Spring Boot, Thymeleaf and an SMTP server (e.g. Gmail or Brevo).

## Tech Stack

| Component        | Version                     |
|------------------|-----------------------------|
| Java (JDK)       | 25                          |
| Spring Boot      | 4.1.x                       |
| Thymeleaf        | via `spring-boot-starter-thymeleaf` + Layout Dialect |
| Jakarta Mail     | via `spring-boot-starter-mail` |
| Lombok           | managed by Spring Boot      |
| Build tool       | Maven Wrapper (Maven 3.9.11) |

## Prerequisites

- JDK 25 (e.g. [Temurin](https://adoptium.net/) or Amazon Corretto)
- An SMTP account (Gmail App Password, Brevo SMTP key, etc.)

No local Maven installation is required — use the bundled `./mvnw`.

## Configuration

SMTP settings live in `src/main/resources/application.properties`:

```properties
spring.mail.default-encoding=UTF-8
spring.mail.host=smtp.gmail.com
spring.mail.username=youremail@gmail.com
spring.mail.password=yourpassword
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> ⚠️ **Never commit real credentials.** Prefer environment variables, which Spring Boot maps automatically:
>
> ```bash
> export SPRING_MAIL_USERNAME=youremail@gmail.com
> export SPRING_MAIL_PASSWORD=your-app-password
> ```

Sender and recipient addresses are set in
`SpringbootEmailThymeleafTemplateSampleApplication#run` — replace them with your own.

## Build & Run

```bash
# build and run the tests
./mvnw clean verify

# run the application (sends one email on startup)
./mvnw spring-boot:run
```

The tests mock `JavaMailSender`, so `./mvnw verify` renders the Thymeleaf template and builds the MIME
message **without** connecting to a real SMTP server.

## Project Structure

```
src/main/java/.../
├── SpringbootEmailThymeleafTemplateSampleApplication.java  # ApplicationRunner that sends the email
├── config/ThymeleafTemplateConfig.java                     # Thymeleaf template engine for emails
├── model/Mail.java                                         # mail DTO (from, to, subject, props)
└── service/EmailSenderService.java                         # builds & sends the MIME message
src/main/resources/
├── templates/newsletter-template.html                      # Thymeleaf email template
└── images/                                                 # attachment & inline images
```

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests the project on JDK 25 (Temurin)
  for every push and pull request to `master`, and submits the Maven dependency graph on pushes to `master`.
- **Dependabot** (`.github/dependabot.yml`) checks Maven dependencies and GitHub Actions weekly
  (Monday 05:00 Asia/Jakarta), groups related updates (Spring Boot, Maven plugins, minor/patch, Actions)
  into fewer PRs, and labels them with `dependencies`.

## What is Thymeleaf
> Thymeleaf is a modern server-side Java template engine for both web and standalone environments. HTML templates written in Thymeleaf still look and work like HTML.

## Thymeleaf HTML email template

Using Thymeleaf for processing our email templates would allow us to use some interesting features:

1. Expressions in Spring EL.
2. Flow control: iterations, conditionals, etc.
3. Utility functions: date/number formatting, dealing with lists, arrays…
4. Easy i18n, integrated with our application’s Spring internationalization infrastructure.
5. Natural templating: our email templates can be static prototypes, written by UI designers.

Also, given the fact that Thymeleaf has no required dependencies on the servlet API, there would be no need at all to
create or send email from a web application. In this example we are going to create a very simple HTML template.
