package com.healthcare.enrollment.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EnrolleeVO {
	private Long id;
	private String name;
	private boolean activationStatus;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private String phoneNumber;
	private List<DependentVO> dependents;

	public EnrolleeVO() {
	}

	public EnrolleeVO(Long id, String name, boolean activationStatus, Date dob, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.activationStatus = activationStatus;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<DependentVO> getDependents() {
		return dependents;
	}

	public void setDependents(List<DependentVO> dependents) {
		this.dependents = dependents;
	}

}
