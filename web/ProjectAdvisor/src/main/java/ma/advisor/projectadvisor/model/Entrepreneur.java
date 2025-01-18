package ma.advisor.projectadvisor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entrepreneur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //Login information
    private String email;
    private String password;
    //account information
    private String firstname;
    private String lastname;
    //profile information
    private String education;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Sexe gender;
    private String traitCles;

}
