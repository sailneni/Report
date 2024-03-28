package com.sai.util;

import java.io.File;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body, String to, File file) {
		
		try {
			
			MimeMessage mineMsg =mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mineMsg, true);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			helper.addAttachment("Plans", file);
			
			mailSender.send(mineMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
