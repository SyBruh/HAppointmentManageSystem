package sg.nus.iss.service.interfacemethod;

import java.time.LocalTime;
import java.util.List;

import sg.nus.iss.service.model.Schedule;
import sg.nus.iss.service.model.Staff;

public interface ScheduleService {
	List<Schedule> getdoctorSchedule(int id);
	void increaseslot(Staff staff,int count, LocalTime time);
}
