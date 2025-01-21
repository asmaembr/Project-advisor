package ma.advisor.projectadvisor.repository;

import ma.advisor.projectadvisor.model.Entrepreneur;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, UUID> {
    Entrepreneur getEntrepreneurByEmail(String email);
}
