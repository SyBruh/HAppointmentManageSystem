package sg.nus.iss.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.service.model.Staff;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
	@Query("Select s from Staff s where s.designation = 'doctor' AND s.department.id = :did")
	public List<Staff> getdoctorsbydepartment(@Param("did") int did);
	@Query("Select s from Staff s where s.designation = 'doctor'")
	public List<Staff> getdoctors();
}
