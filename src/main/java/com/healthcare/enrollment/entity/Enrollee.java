package com.healthcare.enrollment.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
@Table(name = "ENROLEES")
@Data
public class Enrollee implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	@ApiModelProperty(required=false, hidden=true)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ACTIVATION_STATUS")
	private boolean activationStatus;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@OneToMany
	@JoinTable(name = "ENROLEE_DEPENDENT", joinColumns = @JoinColumn(name = "ENROLEE_ID"), inverseJoinColumns = @JoinColumn(name = "DEPENDENT_ID"))
	private List<Dependent> dependents;


	@Override
	public String toString() {
		return "EnroleeModal [id=" + id + ", name=" + name + ", activationStatus=" + activationStatus + ", dob=" + dob
				+ ", phoneNumber=" + phoneNumber + ", dependents=" + dependents + "]";
	}

}