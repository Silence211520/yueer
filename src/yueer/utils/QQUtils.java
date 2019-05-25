package yueer.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * QQ邮箱邮件发送工具类
 * @author silence
 *
 */
public class QQUtils {
	public static String email="157241092@qq.com";
	// 授权码
	public static String password = "kuwwbntdmdbnbjfa";
	// html format email
	private static final String MAIL_TYPE_HTML = "text/html;charset=utf-8";
	// text format email
	private static final String MAIL_TYPE_TEXT = "text/plain;charset=utf-8";
	/**
	 * QQ邮件邮件发送配置信息
	 * @return
	 */
	private static Properties sendProperties(){
		Properties pro = new Properties();
		pro.setProperty("mail.smtp.host", "smtp.qq.com");
		pro.setProperty("mail.smtp.port", "465");
		pro.setProperty("mail.smtp.auth", "true");
		pro.setProperty("mail.smtp.ssl.enable", "true");
		pro.setProperty("mail.transport.protocol", "smtp");
		return pro;
	}
   /**
    * QQ邮件服务器接收邮件的配置信息
    */
	@Deprecated
	private static Properties receiverProperties(){
		Properties pro = new Properties();
		pro.setProperty("mail.store.protocol", "imap");
		pro.setProperty("mail.imap.host", "imap.qq.com");
		pro.setProperty("mail.imap.port", "993");
		pro.setProperty("mail.imap.ssl.enable", "true");
		return pro;
	}
 public static Session getSendSession() {
	 Session session = Session.getInstance(QQUtils.sendProperties());
	 // 开启日志信息
	 session.setDebug(true);
	 return session;
 }
 /**
  *
  * @param to     收件人
  * @param copy    抄送人
  * @param content  邮件的内容
  * @param Subject  邮件的主题
  * @return
  */
 public static boolean sendMail(String to,String copy,String content,String Subject) {
	 boolean send_ok = false;
	 Session session = QQUtils.getSendSession();
	 Message msg = new MimeMessage(session);
	 Transport trans;
	try {
		trans = session.getTransport();
		trans.connect(QQUtils.email, QQUtils.password);
		 msg.setFrom(new InternetAddress(QQUtils.email) );
		 // 邮件发送的时间 2018-2-1
		 msg.setSentDate(new Date(1517466600));
		 msg.setSubject(Subject);
		 // bcc  为抄送
		msg.setRecipient(RecipientType.BCC, new InternetAddress(copy));
		// to 为收件人
		msg.setRecipient(RecipientType.TO, new InternetAddress(to));
		msg.setContent(content, QQUtils.MAIL_TYPE_HTML);
		trans.sendMessage(msg, msg.getAllRecipients());
		send_ok = true;
		return send_ok;
		
	} catch (NoSuchProviderException e) {
		return send_ok;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		return send_ok;
	}
 }
 public static boolean sendMail(String to,String content,String subject){
	 // 标记邮件发送的状态
	 boolean send_ok = false;
	Session session = QQUtils.getSendSession();
	Message msg = new MimeMessage(session);
	try {
		Transport trans = session.getTransport();
		trans.connect(QQUtils.email, QQUtils.password);
		msg.setFrom(new InternetAddress(QQUtils.email));
		msg.setRecipient(RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		// html 格式的邮件
		msg.setContent(content, QQUtils.MAIL_TYPE_HTML);
		trans.sendMessage(msg, msg.getAllRecipients());
		send_ok = true;
		
		return send_ok;
	} catch (NoSuchProviderException e) {
		return send_ok;
	} catch (AddressException e) {
		return send_ok;
	} catch (MessagingException e) {
		return send_ok;
	}
	finally {
		System.out.println(send_ok);
	}
 }
 public static void main(String[] args) {
	 String to = "silence_2022@163.com";
	 String copy = "2512765684@qq.com";
	 StringBuilder sb = new StringBuilder();
	 String subject = "供暖不正常";
	 sb.append("尊敬的张先生<i>[hd_js_2901]<i>:<br>");
	 sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统监控显示：您最近供暖出现异常,我们已通过邮件方式反馈给相关的技术人员,修复完成后,我们会第一时间通知与您!<br>");
	 sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i>如果出现供暖异常情形,期待您与我们及时保持联系,我们的邮箱账号为:<mark>2512765684@qq.com</mark></i>");
	QQUtils.sendMail(to, copy, sb.toString(),subject );
}
}
