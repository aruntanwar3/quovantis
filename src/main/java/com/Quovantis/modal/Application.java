package com.Quovantis.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.Quovantis.constant.ApplicationStatus;



@Entity
@Table(name = "application", uniqueConstraints=@UniqueConstraint(columnNames={"OFFER_ID","EMAIL"}))
public class Application {

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "RESUME_TEXT")
	private String resumeText;
	
	@Column(name="EMAIL")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name="APPLICATION_STATUS")
	private ApplicationStatus applicationStatus;
	
	@Column(name="OFFER_ID", insertable=false, updatable=false)
	private Long offerId;
	
	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	@ManyToOne
	@JoinColumn(name="OFFER_ID", referencedColumnName = "ID")
	private Offer offer;

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

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
}
