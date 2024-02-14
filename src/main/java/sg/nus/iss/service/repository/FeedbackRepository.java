package sg.nus.iss.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.service.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{
	
	@Query("Select f From Feedback f Where f.appointment.staff.id = :id")
	public List<Feedback> getfeedbacksbydoctorid(@Param("id") int id);
}
