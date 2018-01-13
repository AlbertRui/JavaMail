package me.javamail.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送的工具类
 * @author Administrator
 *
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 * @param toUser 发送对象
	 * @param code 激活码
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendMail(String toUser, String code) throws AddressException, MessagingException {
		
		//1.创建连接对象，连接到邮箱服务器
		Properties props = new Properties();
		//props.setProperty(key, value);本地服务器，可省略
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@store.com", "123");
			}
			
		});
 		//2.创建邮件对象
		Message message = new MimeMessage(session);
		//2.1设置发件人
		message.setFrom(new InternetAddress("service@store.com"));
		//2.2设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(toUser));
		//2.3设置邮件主题
		message.setSubject("来自xx网站的激活邮件");
		//2.4设置邮件正文
		message.setContent("<h1>来自XX网站的激活邮件，请点击以下链接激活:</h1><h3><a href='http://localhost:8888/regist_web/ActiveServlet?code="+ code +"'>http://localhost:8888/regist_web/ActiveServlet?code="+ code +"</a></h3>", "text/html;charset=UTF-8");
		//3.发送一封激活邮件
		Transport.send(message);
	}
}
