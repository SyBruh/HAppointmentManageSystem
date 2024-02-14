package sg.nus.iss.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.service.interfacemethod.DiseaseService;
import sg.nus.iss.service.model.Disease;
import sg.nus.iss.service.repository.DiseaseRepository;

@Service
public class DiseaseServiceImpl implements DiseaseService{
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Override
	public Disease findDepartmentByDiseaseName(String name) {
		return diseaseRepository.findDepartmentByDiseaseName(name);
	}
}
