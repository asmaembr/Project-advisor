package ma.advisor.projectadvisor.repository;

import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> getProjectsByEntrepreneur(Entrepreneur entrepreneur);

    void deleteProjectById(Integer id);
}
