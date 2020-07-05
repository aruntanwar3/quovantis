package com.Quovantis.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.Quovantis.constant.ApplicationStatus;

public class ApplicationDto {
	private Long id;
	@NotNull(message = "Resume Text can not be null!")
	private String resumeText;
	@NotNull(message = "Email can not be null!")
	@Email
	private String email;
	@NotNull(message = "Application Status can not be null!")
	private ApplicationStatus applicationStatus;
	@NotNull(message = "Offer Id can not be null!")
	private Long offerId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public Long getOfferId() {
		return offerId;
	}
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	
	
}
