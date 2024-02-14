package sg.nus.iss.service.interfacemethod;

import java.util.List;

import sg.nus.iss.service.model.Staff;

public interface StaffService {
	List<Staff> getdoctorsbyDepartmentID(int did);
	List<Staff> getdoctors();
	Staff findstaffbyid(int id);
}
