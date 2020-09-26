package com.healthcare.enrollment.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DependentVO {
	private Long id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	public DependentVO() {
	}

	public DependentVO(Long id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
