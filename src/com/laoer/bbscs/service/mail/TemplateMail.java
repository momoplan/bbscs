package com.laoer.bbscs.service.mail;

import freemarker.template.*;

import java.io.*;
import java.util.*;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.*;
import org.apache.commons.logging.*;
//import org.springframework.core.io.*;

import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.comm.*;

public class TemplateMail {

	private static final Log logger = LogFactory.getLog(TemplateMail.class);

	private Configuration tempConfiguration = new Configuration();

	private HtmlEmail htmlEmail;

	private SysConfig sysConfig;

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public HtmlEmail getHtmlEmail() {
		return htmlEmail;
	}

	public void setHtmlEmail(HtmlEmail htmlEmail) {
		this.htmlEmail = htmlEmail;
	}

	public Configuration getTempConfiguration() {
		return tempConfiguration;
	}

	public void setTempConfiguration(Configuration tempConfiguration) {
		this.tempConfiguration = tempConfiguration;
	}

	public void init() throws Exception {
		this.htmlEmail = new HtmlEmail();
		if (this.getSysConfig().getSmtpAuth() == 1) {
			DefaultAuthenticator defaultAuthenticator = new DefaultAuthenticator(
					this.getSysConfig().getSmtpUser(), this.getSysConfig()
							.getSmtpPasswd());
			this.getHtmlEmail().setAuthenticator(defaultAuthenticator);
		}

		this.getHtmlEmail().setHostName(this.getSysConfig().getSmtpServer());
		this.getHtmlEmail().setSmtpPort(this.getSysConfig().getSmtpPort());
		this.getHtmlEmail().setFrom(this.getSysConfig().getSenderEmail());
		this.getHtmlEmail().setTextMsg(
				"Your email client does not support HTML messages");
		this.getHtmlEmail().setCharset(Constant.CHARSET);

		this.getTempConfiguration().setDirectoryForTemplateLoading(new File(Constant.ROOTPATH + Constant.FTL_PATH));
		//ClassPathResource cpr = new ClassPathResource("/templates/");
		//System.out.println(cpr.getFile());
		//this.getTempConfiguration().setDirectoryForTemplateLoading(cpr.getFile());
		this.getTempConfiguration().setDefaultEncoding(Constant.CHARSET);
		this.getTempConfiguration().setNumberFormat("0.##########");
	}

	public void sendMailFromTemplate(String to, String subject, String ftlName,
			Map root, Locale locale) {
		try {
			//this.init();
			this.getTempConfiguration().setLocale(locale);

			this.getHtmlEmail().setSubject(subject);
			Template temp = this.getTempConfiguration().getTemplate(ftlName);
			StringWriter sw = new StringWriter();
			temp.process(root, sw);
			this.getHtmlEmail().setHtmlMsg(sw.toString());
			List<InternetAddress> list=new ArrayList<InternetAddress>();
			list.add(new InternetAddress(to));
			//this.getHtmlEmail().addTo(to);
			this.getHtmlEmail().setTo(list);
			this.getHtmlEmail().send();
			sw.flush();
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
