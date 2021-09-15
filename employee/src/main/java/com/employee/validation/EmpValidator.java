package com.employee.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.employee.constants.EmployeeCons;

public class EmpValidator {
	
	private static final String[] paramArr = {"name", "email_id", "phone_number", "salary"};
	
	public String checkParams(String mailId, String phoneNo, String name, String gender) {
		List<String> checker = new ArrayList<>();
		String regex1 = "[A-Z]{1}[a-zA-Z]+";
		if(!name.matches(regex1))
			checker.add(EmployeeCons.INVALID_NAME);
		String regex2 = "[a-z][a-z0-9]+[@][a-z]+[.][a-z]+";
		if(!mailId.matches(regex2))
			checker.add(EmployeeCons.INVALID_MAIL);
		String regex3 = "[0-9]{10}";
		if(!phoneNo.matches(regex3))
			checker.add(EmployeeCons.INVALID_PHONE_NO);
		String regex4 = "[M|F|O]";
		if(!gender.matches(regex4))
			checker.add(EmployeeCons.INVALID_GENDER);
		
		return checker.isEmpty() ? "" : checker.stream().collect(Collectors.joining(", "));
	}
	
	public String checkParam(String param, Object value) {
		
		List<String> params = Arrays.asList(paramArr);
		
		if(params.contains(param)) {
			String checker = "";
			switch(param) {
			case "name": 		String regex1 = "[A-Z]{1}[a-zA-Z]+";
								if(!String.valueOf(value).matches(regex1))
									checker = EmployeeCons.INVALID_NAME;
								break;
			case "email_id":	String regex2 = "[a-z][a-z0-9]+[@][a-z]+[.][a-z]+";
								if(!String.valueOf(value).matches(regex2))
									checker = EmployeeCons.INVALID_MAIL;
								break;
			case "phone_number":String regex3 = "[0-9]{10}";
								if(!String.valueOf(value).matches(regex3))
									checker = EmployeeCons.INVALID_PHONE_NO;
								break;
			case "salary": 		Long salary = Long.parseLong(String.valueOf(value));
								if(salary<=0)
									checker = EmployeeCons.INVALID_SALARY;
								break;
			default: 			checker = "";
			}
			return checker;
		}
		else
			return EmployeeCons.VALID_PARAMS;
	}

}
