package ma.advisor.projectadvisor.repository;

import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> getProjectsByEntrepreneurOrderByUpdatedDateDesc(Entrepreneur entrepreneur);
    void deleteProjectById(Long id);
}
