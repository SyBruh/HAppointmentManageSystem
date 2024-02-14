package sg.nus.iss.service.interfacemethod;

import java.util.List;

import sg.nus.iss.service.model.Feedback;

public interface FeedbackService {
	Feedback savefeedback(Feedback feedback);
	List<Feedback> getfeedbackbydoctorid(int id);
}
