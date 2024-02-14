package sg.nus.iss.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.service.interfacemethod.DepartmentService;
import sg.nus.iss.service.model.Department;
import sg.nus.iss.service.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> findalldepartments() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

}
