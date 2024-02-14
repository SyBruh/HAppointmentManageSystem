package sg.nus.iss.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.service.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{
	@Query("Select d FROM Department d JOIN d.diseases dis WHERE dis.id=:id")
	Department findDepartmentByDiseaseId(@Param("id") int id);
}
