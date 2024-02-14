package sg.nus.iss.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.service.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
	@Query("Select P from Patient P where name = :name")
	public Patient CheckPatient(@Param("name") String name);
}
