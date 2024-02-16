package sg.nus.iss.service.interfacemethod;

import java.util.List;

import sg.nus.iss.service.model.Customer;
import sg.nus.iss.service.model.Patient;

public interface CustomerService {
	List<Patient> findpatientsbyUserID(int id);
	Customer UserAuthentication(String name,String password);
	Customer finduserbyid(int id);
	Customer register(Customer customer);
}
