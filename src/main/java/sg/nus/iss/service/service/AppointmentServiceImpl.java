package sg.nus.iss.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.service.interfacemethod.AppointmentService;
import sg.nus.iss.service.model.Appointment;
import sg.nus.iss.service.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository appointmentrepository;
	@Override
	public List<Appointment> getappointmentsbyuserid(int userid) {
		// TODO Auto-generated method stub
		return appointmentrepository.getappointmentsbyuserid(userid);
	}
	@Override
	public Appointment addappointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentrepository.save(appointment);
		return appointment;
	}
	@Override
	public Appointment findappointmentbyid(int id) {
		Optional<Appointment> appointment = appointmentrepository.findById(id);
		// TODO Auto-generated method stub
		return appointment.get();
	}
	@Override
	public Appointment cancel(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentrepository.save(appointment);
		return appointment;
	}
	
	

}
