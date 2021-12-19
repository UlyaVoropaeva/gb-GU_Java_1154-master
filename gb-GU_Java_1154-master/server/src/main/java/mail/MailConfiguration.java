package mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.google.com");
        sender.setPort(587);
        sender.setUsername("<changeit>");
        sender.setPassword("<changeit>");

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smpt.auth", "true");
        props.put("mail.smpt.starttls.enable", "true");
        props.put("mail.debug", "true");

        return sender;
    }
}
