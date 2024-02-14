package sg.nus.iss.service.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.service.interfacemethod.ScheduleService;
import sg.nus.iss.service.model.Schedule;
import sg.nus.iss.service.model.Staff;
import sg.nus.iss.service.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private ScheduleRepository schedulerepository;
	@Override
	public List<Schedule> getdoctorSchedule(int id) {
		// TODO Auto-generated method stub
		return schedulerepository.getdoctorSchedules(id);
	}
	
	@Override
	@Transactional
	public void increaseslot(Staff staff,int count,LocalTime time) {
		// TODO Auto-generated method stub
		schedulerepository.increaseslot(staff, count,time);
	}

}
