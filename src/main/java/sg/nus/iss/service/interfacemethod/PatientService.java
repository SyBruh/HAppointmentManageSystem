package sg.nus.iss.service.interfacemethod;

import sg.nus.iss.service.model.Patient;

public interface PatientService {
	Patient findpatientbyid(int id);
	Boolean addPatient(Patient patient,int UserID);
	Patient Updatepatient(Patient patient, int userid);
	void RemovePaient(int patientid,int userid);
}
