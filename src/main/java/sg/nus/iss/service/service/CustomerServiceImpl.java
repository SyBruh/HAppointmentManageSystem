package sg.nus.iss.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.service.interfacemethod.CustomerService;
import sg.nus.iss.service.model.Customer;
import sg.nus.iss.service.model.Patient;
import sg.nus.iss.service.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerrepository;

	@Override
	public Customer UserAuthentication(String name, String password) {
		// TODO Auto-generated method stub
		Customer customer = customerrepository.UserAuthentication(name, password);
		if(customer!= null) {
			return customer;
		}
		return null;
	}

	@Override
	public List<Patient> findpatientsbyUserID(int id) {
		// TODO Auto-generated method stub
		return customerrepository.getPatientsbyUser(id);
	}

	@Override
	public Customer finduserbyid(int id) {
		Optional<Customer> customer = customerrepository.findById(id);
		// TODO Auto-generated method stub
		return customer.get();
	}

	@Override
	public Customer register(Customer customer) {
		// TODO Auto-generated method stub
		return customerrepository.save(customer);
	}

}
