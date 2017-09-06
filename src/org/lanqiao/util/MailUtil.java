package org.lanqiao.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	static final String SMPTHOST = "smtp.163.com";
	static final String AUTHPASSWORD = "20121221sjmrNS";
	static final String FROM = "m15834366853@163.com";
	public static void sentEmail(String to,String loginid,String url,String type) {
		
		Authenticator authenticator = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("m15834366853@163.com", "20121221");
			}
		};
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", SMPTHOST);
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(properties,authenticator);
		
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress(FROM, "电子书城", "UTF-8"));
			mimeMessage.setSubject("您的 电子书城 账号，请确认您的电子邮件");
			mimeMessage.setSentDate(new Date());
			mimeMessage.setContent("尊敬的 "+loginid+" :<br>"+"要完成 电子书城 帐户注册，请先确认您的电子邮件地址。点击下面的链接或将链接复制到地址栏中打开。<br><a href='" + url + "'>"+url+"</a>", "text/html;charset=UTF-8");
			mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to, loginid, "UTF-8"));
			
			Transport transport = session.getTransport();
			transport.connect(FROM, AUTHPASSWORD);
			transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
			transport.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
