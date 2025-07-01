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
import javax.persistence.Transient;

/**
 * @author SSoumya
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mails_data")
public class MailsData {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name = "receivers_name")
	private String receiversName;

	@Column(name = "receivers_email")
	private String receiversEmail;

	@Column(name = "level")
	private String level;

	@Column(name = "location")
	private String location;

	@Column(name = "industry")
	private String industry;
	
	@Column(name = "contact_originator_name")
	private String contactOriginatorName;

	@Column(name = "contact_originator_email")
	private String contactOriginatorEmail;

	@Column(name = "contact_originator_role")
	private String contactOriginatorRole;
	
	@Column(name = "contact_originator_password")
	private String contactOriginatorPassword;
	
	@Column(name="format_id")
	private int formatId ;
	
	@Column(name="created_on")
	private Timestamp createdOn;
	
	@Column(name="bounce_type_id")
	private int bounceTypeId;

	@Column(name="Soft_bounce_count")
	private int softBounceCount;
	
	@Transient
	private Integer filterCount ;
	
	public int getBounceTypeId() {
		return bounceTypeId;
	}

	public void setBounceTypeId(int bounceTypeId) {
		this.bounceTypeId = bounceTypeId;
	}

	public int getSoftBounceCount() {
		return softBounceCount;
	}

	public void setSoftBounceCount(int softBounceCount) {
		this.softBounceCount = softBounceCount;
	}

	public MailsData() {
		
	}
	
	public MailsData(Integer id, String receiversName, String receiversEmail, String level, String location,
			String industry, String contactOriginatorName, String contactOriginatorEmail, String contactOriginatorRole,
			String contactOriginatorPassword, int formatId, Timestamp createdOn, int bounceTypeId, int softBounceCount,
			Integer filterCount) {
		Id = id;
		this.receiversName = receiversName;
		this.receiversEmail = receiversEmail;
		this.level = level;
		this.location = location;
		this.industry = industry;
		this.contactOriginatorName = contactOriginatorName;
		this.contactOriginatorEmail = contactOriginatorEmail;
		this.contactOriginatorRole = contactOriginatorRole;
		this.contactOriginatorPassword = contactOriginatorPassword;
		this.formatId = formatId;
		this.createdOn = createdOn;
		this.bounceTypeId = bounceTypeId;
		this.softBounceCount = softBounceCount;
		this.filterCount = filterCount;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getContactOriginatorName() {
		return contactOriginatorName;
	}

	public void setContactOriginatorName(String contactOriginatorName) {
		this.contactOriginatorName = contactOriginatorName;
	}

	public String getContactOriginatorEmail() {
		return contactOriginatorEmail;
	}

	public void setContactOriginatorEmail(String contactOriginatorEmail) {
		this.contactOriginatorEmail = contactOriginatorEmail;
	}

	public String getContactOriginatorRole() {
		return contactOriginatorRole;
	}

	public void setContactOriginatorRole(String contactOriginatorRole) {
		this.contactOriginatorRole = contactOriginatorRole;
	}

	public String getContactOriginatorPassword() {
		return contactOriginatorPassword;
	}

	public void setContactOriginatorPassword(String contactOriginatorPassword) {
		this.contactOriginatorPassword = contactOriginatorPassword;
	}

	public int getFormatId() {
		return formatId;
	}

	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getFilterCount() {
		return filterCount;
	}

	public void setFilterCount(Integer filterCount) {
		this.filterCount = filterCount;
	}

	@Override
	public String toString() {
		return "MailsData [Id=" + Id + ", receiversName=" + receiversName + ", receiversEmail=" + receiversEmail
				+ ", level=" + level + ", location=" + location + ", industry=" + industry + ", contactOriginatorName="
				+ contactOriginatorName + ", contactOriginatorEmail=" + contactOriginatorEmail
				+ ", contactOriginatorRole=" + contactOriginatorRole + ", contactOriginatorPassword="
				+ contactOriginatorPassword + ", formatId=" + formatId + ", createdOn=" + createdOn + ", bounceTypeId="
				+ bounceTypeId + ", softBounceCount=" + softBounceCount + ", filterCount=" + filterCount + "]";
	}
	
	
}
