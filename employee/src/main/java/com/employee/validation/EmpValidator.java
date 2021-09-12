package com.employee.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.employee.constants.EmployeeCons;

public class EmpValidator {
	
	public String checkParams(String mailId, String phoneNo, String name, String gender) {
		List<String> checker = new ArrayList<>();
		String regex1 = "[A-Z]{1}[a-zA-Z]+";
		if(!name.matches(regex1))
			checker.add(EmployeeCons.NON_NULL_NAME);
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

}
