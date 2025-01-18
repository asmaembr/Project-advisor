package ma.advisor.projectadvisor.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    public String Education;
    public String ProjetIndividuel;
    public Integer Age;
    public String Genre;
    public String EnVille;
    public String Influence;
    public int DegPerseverance;
    public int DegInitiative;
    public int DegCompetition;
    public int DegAutonomie;
    public int DegBesoinReussite;
    public int DegConfiance;
    public int DegSante;
    public String TroubleMental;
    public String TraitsCles;

}
