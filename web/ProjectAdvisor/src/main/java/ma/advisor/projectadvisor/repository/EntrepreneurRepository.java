package ma.advisor.projectadvisor.repository;

import ma.advisor.projectadvisor.model.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Integer> {
    Entrepreneur getEntrepreneurByEmail(String email);
}
