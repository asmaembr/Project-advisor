package ma.advisor.projectadvisor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private String troubleMental;
    private String traitCles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sexe getGender() {
        return gender;
    }

    public void setGender(Sexe gender) {
        this.gender = gender;
    }

    public String getTroubleMental() {
        return troubleMental;
    }

    public void setTroubleMental(String troubleMental) {
        this.troubleMental = troubleMental;
    }

    public String getTraitCles() {
        return traitCles;
    }

    public void setTraitCles(String traitCles) {
        this.traitCles = traitCles;
    }
}
