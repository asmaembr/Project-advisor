package ma.advisor.projectadvisor.service;

import jakarta.transaction.Transactional;
import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.model.Project;
import ma.advisor.projectadvisor.repository.EntrepreneurRepository;
import ma.advisor.projectadvisor.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return projectRepository.getProjectsByEntrepreneurOrderByUpdatedDateDesc(entrepreneur);
    }


    public void saveProject(Project project) {
        project.setUpdatedDate(LocalDateTime.now());
        projectRepository.save(project);
    }
    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteProjectById(id);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).get();
    }
}
