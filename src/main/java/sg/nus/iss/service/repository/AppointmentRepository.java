package sg.nus.iss.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.service.model.Appointment;
import sg.nus.iss.service.model.AppointmentStatusEnum;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer>{
	
	@Query("Select a From Appointment a where a.customer.id = :customerid")
	public List<Appointment> getappointmentsbyuserid(@Param("customerid") int customerid);
	
	@Modifying
	@Query("Update Appointment a set status = :status Where id = :id")
	public void updateStatus(@Param("id") int id, @Param("status") AppointmentStatusEnum status);

}
