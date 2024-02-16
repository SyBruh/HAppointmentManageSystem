package sg.nus.iss.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.service.model.Customer;
import sg.nus.iss.service.model.Patient;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	@Query("Select c From Customer c Where name = :n And password = :p")
	public Customer UserAuthentication(@Param("n") String name,@Param("p") String password);
	@Query("Select c.patients From Customer c Where id = :id")
	public List<Patient> getPatientsbyUser(@Param("id") int id);
	@Modifying
	@Query(value = "Insert into Customer_Patient (customer_id,patient_id) VALUES (:customerid, :patientid)", nativeQuery = true)
	public void insertUserPatientRelation(@Param("customerid") int customerid,@Param("patientid") int patientid);
	
	@Modifying
	@Query(value = "Delete From Customer_Patient cp Where customer_id = :customerid AND patient_id = :patientid",  nativeQuery = true)
	public void RemveUserPatientRelation(@Param("customerid") int customerid,@Param("patientid") int patientid);
}

