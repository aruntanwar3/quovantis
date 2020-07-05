package com.Quovantis.modal;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "offer")
public class Offer {
	
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name ="JOB_TITLE", unique = true)
	private String jobTitle;

	@Column(name = "START_DATE")
	private String startDate;
	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Application> appliations;
	
	@Transient
	private Integer noOfApplications;
	
	public Integer getNoOfApplication() {
		return appliations.size();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Application> getAppliations() {
		return appliations;
	}

	public void setAppliations(List<Application> appliations) {
		this.appliations = appliations;
	}
	
	

}
