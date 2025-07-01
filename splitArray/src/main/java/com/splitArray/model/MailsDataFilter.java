/**
 * 
 */
package com.splitArray.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author SSoumya
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name="mails_data_filter")
public class MailsDataFilter {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "receivers_name")
	private String receiversName;
	
	@Column(name = "receivers_email")
	private String receiversEmail;
	
	@Column(name = "format_id")
	private Integer formatId;
	
	@Column(name = "contact_originator_email")
	private String contactOriginatorEmail;
	
	@Column(name = "customSubject")
	private String customSubject;
	
	@Column(name = "mail_sent")
	private Boolean mailSent;
	
	@Column(name = "sent_on")
	private Timestamp sentOn ;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	
	public String getReceiversName() {
		return receiversName;
	}

	public void setReceiversName(String receiversName) {
		this.receiversName = receiversName;
	}

	public String getReceiversEmail() {
		return receiversEmail;
	}

	public void setReceiversEmail(String receiversEmail) {
		this.receiversEmail = receiversEmail;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	public String getContactOriginatorEmail() {
		return contactOriginatorEmail;
	}

	public void setContactOriginatorEmail(String contactOriginatorEmail) {
		this.contactOriginatorEmail = contactOriginatorEmail;
	}

	public String getCustomSubject() {
		return customSubject;
	}

	public void setCustomSubject(String customSubject) {
		this.customSubject = customSubject;
	}

	public Boolean getMailSent() {
		return mailSent;
	}

	public void setMailSent(Boolean mailSent) {
		this.mailSent = mailSent;
	}

	public Timestamp getSentOn() {
		return sentOn;
	}

	public void setSentOn(Timestamp sentOn) {
		this.sentOn = sentOn;
	}

	@Override
	public String toString() {
		return "MailsDataFilter [Id=" + Id + ", recieversName=" + receiversName + ", recieversEmail=" + receiversEmail
				+ ", formatId=" + formatId + ", contactOriginatorEmail=" + contactOriginatorEmail + ", customSubject="
				+ customSubject + ", mailSent=" + mailSent + ", sentOn=" + sentOn + "]";
	}
	
}
