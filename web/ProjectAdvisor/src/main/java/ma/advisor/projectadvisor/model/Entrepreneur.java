package ma.advisor.projectadvisor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrepreneur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //Login information
    private String username;
    private String password;
    //account information
    private String firstname;
    private String lastname;
    private String email;
    //profile information
    private String education;
    private Integer age;
    private Sexe gender;
    private String TroubleMental;
    private String TraitCles;

}
