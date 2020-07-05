package com.Quovantis.dto;


import static com.Quovantis.constant.ApplicationConstant.DATE_FORMAT;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.Quovantis.anotation.ValidDate;

public class OfferDto {
	private Long id;
	@NotBlank
	@NotNull(message = "Job title can not be null!")
	private String jobTitle;
	@NotNull(message = "Start Date can not be null!")
	@ValidDate(message = "Invalid Date. please enter a valid date format " + DATE_FORMAT)
	private String startDate;
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
