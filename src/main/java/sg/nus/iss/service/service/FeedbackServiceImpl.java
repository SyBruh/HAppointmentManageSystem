package sg.nus.iss.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.service.interfacemethod.FeedbackService;
import sg.nus.iss.service.model.Feedback;
import sg.nus.iss.service.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackrepository;

	@Override
	@Transactional
	public Feedback savefeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackrepository.save(feedback);
	}

	@Override
	public List<Feedback> getfeedbackbydoctorid(int id) {
		// TODO Auto-generated method stub
		return feedbackrepository.getfeedbacksbydoctorid(id);
	}

}
