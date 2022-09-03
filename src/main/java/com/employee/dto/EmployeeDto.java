package com.employee.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Email")
	private String emailId;
	@JsonProperty("Phone")
	private String phoneNumber;
	@JsonProperty("Salary")
	private Long salary;
	@JsonProperty("Gender")
	private String gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
