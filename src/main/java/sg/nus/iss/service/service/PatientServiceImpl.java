package sg.nus.iss.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.service.interfacemethod.PatientService;
import sg.nus.iss.service.model.Patient;
import sg.nus.iss.service.repository.CustomerRepository;
import sg.nus.iss.service.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private CustomerRepository customerrepository;

	@Transactional
	@Override
	public Boolean addPatient(Patient patient,int UserID) {
		Patient check = patientRepository.CheckPatient(patient.getName());
		if (check == null) {
			try {
				Patient saved = patientRepository.save(patient);
				customerrepository.insertUserPatientRelation(UserID, saved.getId());
				return true;
			} catch (Exception e) {
				return false;
			}
		}else {
			customerrepository.insertUserPatientRelation(UserID, check.getId());
			return true;
		}

	}

	@Override
	public Patient findpatientbyid(int id) {
		Optional<Patient> patient = patientRepository.findById(id);
		// TODO Auto-generated method stub
		return patient.get();
	}

	@Override
	@Transactional
	public Patient Updatepatient(Patient patient, int userid) {
		// TODO Auto-generated method stub
		Patient savepatient = patientRepository.save(patient);
		customerrepository.insertUserPatientRelation(userid, savepatient.getId());
		return savepatient;
	}

}
