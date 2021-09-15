package com.employee.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.constants.EmployeeCons;
import com.employee.dao.EmployeeDao;
import com.employee.dto.UpdateDto;
import com.employee.dto.UpdateResponseDto;
import com.employee.entity.Employee;
import com.employee.exception.EmpException;
import com.employee.validation.EmpValidator;

@Service
@Transactional
public class EmployeeService {
	
	@Value("${spring.datasource.url}")
	private String db_url;
	
	@Value("${spring.datasource.username}")
	private String db_user;
	
	@Value("${spring.datasource.password}")
	private String db_pass;
	
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
	
	public UpdateResponseDto updateEmployee(UpdateDto updateDto) throws Exception {
		if(!employeeDao.existsById(updateDto.getId()))
			throw new EmpException(EmployeeCons.NO_EXIST);
		EmpValidator validator = new EmpValidator();
		String checker = validator.checkParam(updateDto.getParam(), updateDto.getValue());
		if(!checker.equals(""))
			throw new EmpException(checker);
		Connection conn = DriverManager.getConnection(db_url, db_user, db_pass);
		Statement st = conn.createStatement();
		List<String> queries = getQueries(updateDto.getId(), updateDto.getParam(), updateDto.getValue());
		st.execute(queries.get(0));
		ResultSet rs = st.executeQuery(queries.get(1));
		UpdateResponseDto resp = new UpdateResponseDto();
		while(rs.next()) {
			resp.setId(rs.getLong("id"));
			resp.setName(rs.getString("name"));
			resp.setEmailId(rs.getString("email_id"));
			resp.setSalary(rs.getLong("salary"));
			resp.setPhoneNumber(rs.getString("phone_number"));
			resp.setGender(rs.getString("gender"));
			resp.setStatus("Updated");
		}
		st.close();
		conn.close();
		return resp;
	}
	
	private List<String> getQueries(Long id, String param, Object value) {
		List<String> queries = new ArrayList<>();
		String updateQuery = (value instanceof String) ? 
				("update employees set "+param+" = '"+value+"' where id = "+id) :
					("update employees set "+param+" = "+value+" where id = "+id);
		String getQuery = "select * from employees where id = "+id;
		queries.add(updateQuery);
		queries.add(getQuery);
		return queries;
	}

}
