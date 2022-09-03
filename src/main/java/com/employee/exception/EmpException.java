package com.employee.exception;

public class EmpException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String emsg;
	
	public EmpException(String emsg) {
		super(emsg);
		this.emsg = emsg;
	}
	
	public String getEMsg() {
		return emsg;
	}

}
