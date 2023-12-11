package kr.co.waglewagle.users.ty.mailauth;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.naver.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("teayun357@naver.com");
		javaMailSender.setPassword("BW1H2SSV7SVK");
		
		javaMailSender.setJavaMailProperties(getMailProperties());
		
		return javaMailSender;//
	}
	
	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "true");
		properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
		//properties.setProperty("mail.smtp.ssl.enable", "false");
		properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		
		
		return properties;
	}
}
