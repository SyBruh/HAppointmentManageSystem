package sg.nus.iss.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.service.interfacemethod.StaffService;
import sg.nus.iss.service.model.Staff;
import sg.nus.iss.service.repository.StaffRepository;
@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffrepository;
	@Override
	public List<Staff> getdoctorsbyDepartmentID(int did) {
		// TODO Auto-generated method stub
		return staffrepository.getdoctorsbydepartment(did);
	}

	@Override
	public List<Staff> getdoctors() {
		// TODO Auto-generated method stub
		return staffrepository.getdoctors();
	}

	@Override
	public Staff findstaffbyid(int id) {
		Optional<Staff> staff= staffrepository.findById(id);
		// TODO Auto-generated method stub
		return staff.get();
	}

}
