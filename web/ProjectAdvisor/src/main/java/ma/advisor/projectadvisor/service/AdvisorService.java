package ma.advisor.projectadvisor.service;

import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.repository.EntrepreneurRepository;
import ma.advisor.projectadvisor.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvisorService {
    @Autowired
    private AdvisorProxy advisorProxy;
    @Autowired
    private EntrepreneurRepository entrepreneurRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public void saveEntrepreneur(Entrepreneur entrepreneur) {
        entrepreneurRepository.save(entrepreneur);
    }


    public void ProfilePrediction(Entrepreneur entrepreneur) {

    }
}
