package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.constants.EmployeeCons;
import com.employee.dao.EmployeeDao;
import com.employee.entity.Employee;
import com.employee.exception.EmpException;
import com.employee.validation.EmpValidator;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public Employee getEmployee(Long id) throws Exception {
		if(!employeeDao.existsById(id))
			throw new EmpException(EmployeeCons.NO_EXIST);
		return employeeDao.findById(id).get();
	}
	
	public String createEmployee(Employee emp) throws Exception {
		EmpValidator validator = new EmpValidator();
		String validResult = validator.checkParams(emp.getEmailId(), emp.getPhoneNumber(), emp.getName(), emp.getGender());
		if(!validResult.equals(""))
			throw new EmpException(validResult);
		employeeDao.save(emp);
		return EmployeeCons.EMP_CREATED;
	}
	
	public String deleteEmployee(Long id) throws Exception {
		if(!employeeDao.existsById(id))
			throw new EmpException(EmployeeCons.NO_EXIST);
		employeeDao.deleteById(id);
		return EmployeeCons.EMP_DELETED;
	}

}
