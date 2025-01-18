package ma.advisor.projectadvisor.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profile {
    private String education;
    private String projetIndividuel;
    private Integer age;
    private String genre;
    private String enVille;
    private String influence;
    private Integer degPerseverance;
    private Integer degInitiative;
    private Integer degCompetition;
    private Integer degAutonomie;
    private Integer degBesoinReussite;
    private Integer degConfiance;
    private Integer degSante;
    private String troubleMental;
    private String traitsCles;
}
