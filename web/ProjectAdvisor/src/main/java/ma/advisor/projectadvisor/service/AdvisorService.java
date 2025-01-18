package ma.advisor.projectadvisor.service;

import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.model.Project;
import ma.advisor.projectadvisor.repository.EntrepreneurRepository;
import ma.advisor.projectadvisor.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisorService {
    @Autowired
    private EntrepreneurRepository entrepreneurRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public void saveEntrepreneur(Entrepreneur entrepreneur) {
        entrepreneurRepository.save(entrepreneur);
    }

    public Entrepreneur getEntrepreneur(String email) {
        return entrepreneurRepository.getEntrepreneurByEmail(email);
    }

    public List<Project> getProjects(Entrepreneur entrepreneur) {
        return projectRepository.getProjectsByEntrepreneur(entrepreneur);
    }


    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteProjectById(id);
    }

    public Project getProject(int id) {
        return projectRepository.findById(id).get();
    }
}
