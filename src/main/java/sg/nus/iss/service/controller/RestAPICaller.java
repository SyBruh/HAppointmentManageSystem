package sg.nus.iss.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import sg.nus.iss.service.model.Appointment;
import sg.nus.iss.service.model.AppointmentStatusEnum;
import sg.nus.iss.service.model.Customer;
import sg.nus.iss.service.model.Department;
import sg.nus.iss.service.model.Feedback;
import sg.nus.iss.service.model.Patient;
import sg.nus.iss.service.model.Schedule;
import sg.nus.iss.service.model.Staff;
import sg.nus.iss.service.model.Symptoms;
import sg.nus.iss.service.model.SymptomsSelected;
import sg.nus.iss.service.service.AppointmentServiceImpl;
import sg.nus.iss.service.service.DepartmentServiceImpl;
import sg.nus.iss.service.service.DiseaseServiceImpl;
import sg.nus.iss.service.service.FeedbackServiceImpl;
import sg.nus.iss.service.service.PatientServiceImpl;
import sg.nus.iss.service.service.ScheduleServiceImpl;
import sg.nus.iss.service.service.StaffServiceImpl;
import sg.nus.iss.service.service.CustomerServiceImpl;
import sg.nus.iss.service.service.keyWordsApiService;
import sg.nus.iss.service.service.predictDiseaseApiService;


@RestController
@CrossOrigin
@RequestMapping("api")
public class RestAPICaller {
	@Autowired
	private CustomerServiceImpl uservice;
	@Autowired
	private PatientServiceImpl pservice;
	@Autowired
	private DepartmentServiceImpl dservice;
	@Autowired
	private StaffServiceImpl sservice;
	@Autowired
	private AppointmentServiceImpl aservice;
	@Autowired
	private ScheduleServiceImpl scheservice;
	@Autowired
	private DiseaseServiceImpl disservice;
	@Autowired
	private FeedbackServiceImpl fservice;
	//Disease Prediction
	@Autowired
	private Symptoms symptoms;
	@Autowired
	private predictDiseaseApiService predictApi;
	//Keyword Prediction
	@Autowired
	private keyWordsApiService keywordsApi;
	
	@GetMapping("/checkuser")
	public Customer UserAuthentication(@RequestParam("name") String name, @RequestParam("password") String password) {
		if(uservice.UserAuthentication(name, password)!=null) {
			return uservice.UserAuthentication(name, password);
		}
		return null;
	}
	
	@GetMapping("/viewpatient")
	public ResponseEntity<List<Patient>> GetPatients(@RequestParam("id") int id){
		try {
			List<Patient> patients = uservice.findpatientsbyUserID(id);
			return new ResponseEntity<List<Patient>>(patients,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/viewpatientdetail")
	public ResponseEntity<Patient> GetPatientdetail(@RequestParam("id") int id){
		try {
			Patient patient = pservice.findpatientbyid(id);
			return new ResponseEntity<Patient>(patient,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/addpatient/{userid}")
	public ResponseEntity<Patient> AddPatient(@ModelAttribute("patient") Patient patient,@PathVariable("userid") int userid){
		try {
			pservice.addPatient(patient,userid);
			return new ResponseEntity<Patient>(patient,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Patient>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	@PostMapping("/updatepatient/{id}")
	public ResponseEntity<Patient> updatePatient(@ModelAttribute("patient") Patient patient,@PathVariable("id") int userid){
		try {	
			pservice.Updatepatient(patient,userid);
			return new ResponseEntity<Patient>(patient,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Patient>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	@GetMapping("/getdepartments")
	public ResponseEntity<List<Department>> getalldepartments(){
		try {
			List<Department> departments = dservice.findalldepartments();
			return new ResponseEntity<List<Department>>(departments,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Department>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getdoctors")
	public ResponseEntity<List<Staff>> getdoctors(@RequestParam("id") int id){
		try {
			List<Staff> doctors = new ArrayList<>();
			if(id != 0) {
				doctors = sservice.getdoctorsbyDepartmentID(id);
			}
			else {
				doctors = sservice.getdoctors();
			}
			
			return new ResponseEntity<List<Staff>>(doctors,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Staff>>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getappointments")
	public ResponseEntity<List<Appointment>> getappointmentsbyuserid(@RequestParam("id") int id){
		try {
			List<Appointment> appointments = new ArrayList<>();
			appointments = aservice.getappointmentsbyuserid(id);
			return new ResponseEntity<List<Appointment>>(appointments,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
		}
	} 
	@GetMapping("/getschedules")
	public ResponseEntity<List<Schedule>> getscheduledoctor(@RequestParam("id") int id){
		try {
			List<Schedule> schedules = new ArrayList<>();
			schedules = scheservice.getdoctorSchedule(id);
			return new ResponseEntity<List<Schedule>>(schedules,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Schedule>>(HttpStatus.NOT_FOUND);
		}
	} 
	@PostMapping("/addappointment")
	public ResponseEntity<Appointment> Adddappointment(@ModelAttribute("appointment") Appointment appointment,@RequestParam("userid") int userid,@RequestParam("staffid") int staffid,@RequestParam("patientid") int patientid){
		try {
			//Appointment saveappointment = appointment;
			appointment.setPatient(pservice.findpatientbyid(patientid));
			appointment.setStaff(sservice.findstaffbyid(staffid));
			appointment.setCustomer(uservice.finduserbyid(userid));
			Appointment saveappointment = aservice.addappointment(appointment);
			scheservice.increaseslot(sservice.findstaffbyid(staffid), appointment.getQueue_number(), appointment.getTime());
			return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Appointment>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	@GetMapping("/getSymptomsGroup")
	public ResponseEntity<List<String>> getSymptomsGroup(){
		return new ResponseEntity<List<String>>(symptoms.getSymptomsGroup(),HttpStatus.OK);
	}
	@GetMapping("/getSymptoms/{group}")
	public ResponseEntity<List<String>> getSymptoms(@PathVariable("group") String group){
		return new ResponseEntity<List<String>>(symptoms.getSymptoms(group),HttpStatus.OK);
	}
	
	@GetMapping("/predict")
	public ResponseEntity<Department> getDepartmentbyprediction(@RequestParam("symptoms") List<String> selected,@RequestParam("patientid") int id,@RequestParam("userid") int userid) {
		//Add Patient, //appointmentId to session
		SymptomsSelected selectedSymptoms = new SymptomsSelected();
		selectedSymptoms.setSymptoms(selected);
		Patient patient = pservice.findpatientbyid(id);
//		int appointmentId = (Integer)session.getAttribute("appointmentId");
		
		//Get list of symptoms and convert to string
		List<String> symptomsList = selectedSymptoms.getSymptoms();
		String symptomString = symptomsList.stream()
	            .collect(Collectors.joining(", "));
		
		//Call external api with selected symptoms
		ResponseEntity<String> responseEntity = predictApi.sendSymptomsForPrediction(selectedSymptoms.getSymptoms());
		
		//Extract information from the response
		String apiResponse = responseEntity.getBody();
		
		//Parse JSON response
		String prediction = extractPredictionFromApiResponse(apiResponse);
		HttpStatusCode statusCode = responseEntity.getStatusCode();
		
		//Add Patient medical condition
		String medicalCondition = "Disease: " + prediction + "; Symptoms: " + symptomString;
		patient.setMedical_condition(medicalCondition);
		pservice.Updatepatient(patient, userid);
		//Set appointment department
		//Get disease id from prediction
		Department diseseDepartment = disservice.findDepartmentByDiseaseName(prediction).getDepartment();
		
		return new ResponseEntity<Department>(diseseDepartment,HttpStatus.OK);
	}
	
	private String extractPredictionFromApiResponse(String apiResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(apiResponse);
            return jsonNode.get("prediction").asText();
        } catch (Exception e) {
            return "Error parsing API response";
        }
    }
	
	@PostMapping("/savefeedback")
	public ResponseEntity<Feedback> AddFeedback(@ModelAttribute("feedback") Feedback feedback,@RequestParam("userid") int userid,@RequestParam("appointmentid") int appointmentid){
		try {
			Feedback savefeedback = feedback;
			savefeedback.setCustomer(uservice.finduserbyid(userid));
			savefeedback.setAppointment(aservice.findappointmentbyid(appointmentid));
			fservice.savefeedback(savefeedback);
			return new ResponseEntity<Feedback>(savefeedback,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Feedback>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	@GetMapping("/getfeedbacks")
	public ResponseEntity<List<Feedback>> getfeedbacksbydoctorid(@RequestParam("id") int id){
		try {
			List<Feedback> feedbacks = new ArrayList<>();
			feedbacks = fservice.getfeedbackbydoctorid(id);
			return new ResponseEntity<List<Feedback>>(feedbacks,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Feedback>>(HttpStatus.NOT_FOUND);
		}
	} 
	private List<String> extractKeywordsFromApiResponse(String apiResponse) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(apiResponse);
			JsonNode keywordsNode = jsonNode.get("Top 10 Keywords");
			List<String> keywordsList = new ArrayList<>();
			for (JsonNode keyword : keywordsNode) {
				String keywords = keyword.asText();
				keywordsList.add(keywords);
			}
			return keywordsList;
		} catch (Exception e) {
			List<String> errorList = new ArrayList<>();
			errorList.add("Error parsing API response");
			return errorList;
		}
	}
	
	@GetMapping("/getkeywords")
	private List<String> getkeywords(@RequestParam("feedbacks")List<String> feedbackList){
		StringBuilder allDescriptions = new StringBuilder();

		for (String s : feedbackList) {
			allDescriptions.append(s).append(" ");
		}
		
		String allFeedbackComments = allDescriptions.toString();

		StringBuilder jsonString = new StringBuilder();
		jsonString.append("{");
		jsonString.append("\"text\": \"").append(allFeedbackComments).append("\"");
		jsonString.append("}");
		String jsonStringSend = jsonString.toString();
		ResponseEntity<String> responseEntity = keywordsApi.sendComments(jsonStringSend);
		// Extract information from response
		String apiResponse = responseEntity.getBody();
		// Parse JSON response
		List<String> keyWords = extractKeywordsFromApiResponse(apiResponse);
		return keyWords;
	}
	
	@GetMapping("/cancelappointment/{id}")
	public ResponseEntity<Appointment> cancelappointment(@PathVariable("id") int id){
		try {
			Appointment appointment = aservice.findappointmentbyid(id);
			appointment.setStatus(AppointmentStatusEnum.Cancelled);
			aservice.cancel(appointment);
			return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
		}
	} 
	
	@DeleteMapping("/removepatient/{userid}/{patientid}")
	public ResponseEntity<String> AddPatient(@PathVariable("patientid") int patientid,@PathVariable("userid") int userid){
		try {
			pservice.RemovePaient(patientid, userid);
			return new ResponseEntity<String>("Successful",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/registeruser")
	public ResponseEntity<Customer> AddCustomer(@ModelAttribute("customer") Customer customer){
		try {
			uservice.register(customer);
			return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
}
