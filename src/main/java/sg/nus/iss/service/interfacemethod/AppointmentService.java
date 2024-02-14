package sg.nus.iss.service.interfacemethod;

import java.util.List;

import sg.nus.iss.service.model.Appointment;

public interface AppointmentService {
	
	List<Appointment> getappointmentsbyuserid(int userid);
	Appointment addappointment(Appointment appointment);
	Appointment findappointmentbyid(int id);
	Appointment cancel(Appointment appointment);
}
