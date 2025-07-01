package com.FileOperations.model;

public class DTO {


	private String email;

	private String ipAddress;

	private String name;

	private String caseStudyFile;

	private String phoneNo;

	private String company;

	private String jobTitle;

	private String country;

	private String subject;

	private String message;

	private String demoRequirements;

	private String otp;

	private String formType;

	private String pageName;

	private String emailSubject;

	
	
	public DTO() {
	}

	public DTO(String email, String ipAddress, String name, String caseStudyFile, String phoneNo, String company,
			String jobTitle, String country, String subject, String message, String demoRequirements, String otp,
			String formType, String pageName, String emailSubject) {
		this.email = email;
		this.ipAddress = ipAddress;
		this.name = name;
		this.caseStudyFile = caseStudyFile;
		this.phoneNo = phoneNo;
		this.company = company;
		this.jobTitle = jobTitle;
		this.country = country;
		this.subject = subject;
		this.message = message;
		this.demoRequirements = demoRequirements;
		this.otp = otp;
		this.formType = formType;
		this.pageName = pageName;
		this.emailSubject = emailSubject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaseStudyFile() {
		return caseStudyFile;
	}

	public void setCaseStudyFile(String caseStudyFile) {
		this.caseStudyFile = caseStudyFile;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDemoRequirements() {
		return demoRequirements;
	}

	public void setDemoRequirements(String demoRequirements) {
		this.demoRequirements = demoRequirements;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	@Override
	public String toString() {
		return "DTO [email=" + email + ", ipAddress=" + ipAddress + ", name=" + name + ", caseStudyFile="
				+ caseStudyFile + ", phoneNo=" + phoneNo + ", company=" + company + ", jobTitle=" + jobTitle
				+ ", country=" + country + ", subject=" + subject + ", message=" + message + ", demoRequirements="
				+ demoRequirements + ", otp=" + otp + ", formType=" + formType + ", pageName=" + pageName
				+ ", emailSubject=" + emailSubject + "]";
	}

	

}
