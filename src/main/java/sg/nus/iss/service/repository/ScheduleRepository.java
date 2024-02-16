package sg.nus.iss.service.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.service.model.Schedule;
import sg.nus.iss.service.model.Staff;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer>{
	
	@Query("Select s From Schedule s Where s.staff.id = :id")
	public List<Schedule> getdoctorSchedules(@Param("id") int id);
	
	@Modifying
	@Query("Update Schedule s set patient_slot = :count where s.staff = :staff AND time_start = CAST(:time AS java.sql.Time)")
	public void increaseslot(@Param("staff") Staff staff,@Param("count") int count,@Param("time") LocalTime time);

}
