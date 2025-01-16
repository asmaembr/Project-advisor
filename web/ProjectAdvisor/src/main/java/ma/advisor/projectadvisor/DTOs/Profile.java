package ma.advisor.projectadvisor.DTOs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getProjetIndividuel() {
        return ProjetIndividuel;
    }

    public void setProjetIndividuel(String projetIndividuel) {
        ProjetIndividuel = projetIndividuel;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getEnVille() {
        return EnVille;
    }

    public void setEnVille(String enVille) {
        EnVille = enVille;
    }

    public String getInfluence() {
        return Influence;
    }

    public void setInfluence(String influence) {
        Influence = influence;
    }

    public int getDegPerseverance() {
        return DegPerseverance;
    }

    public void setDegPerseverance(int degPerseverance) {
        DegPerseverance = degPerseverance;
    }

    public int getDegInitiative() {
        return DegInitiative;
    }

    public void setDegInitiative(int degInitiative) {
        DegInitiative = degInitiative;
    }

    public int getDegCompetition() {
        return DegCompetition;
    }

    public void setDegCompetition(int degCompetition) {
        DegCompetition = degCompetition;
    }

    public int getDegAutonomie() {
        return DegAutonomie;
    }

    public void setDegAutonomie(int degAutonomie) {
        DegAutonomie = degAutonomie;
    }

    public int getDegBesoinReussite() {
        return DegBesoinReussite;
    }

    public void setDegBesoinReussite(int degBesoinReussite) {
        DegBesoinReussite = degBesoinReussite;
    }

    public int getDegConfiance() {
        return DegConfiance;
    }

    public void setDegConfiance(int degConfiance) {
        DegConfiance = degConfiance;
    }

    public int getDegSante() {
        return DegSante;
    }

    public void setDegSante(int degSante) {
        DegSante = degSante;
    }

    public String getTroubleMental() {
        return TroubleMental;
    }

    public void setTroubleMental(String troubleMental) {
        TroubleMental = troubleMental;
    }

    public String getTraitsCles() {
        return TraitsCles;
    }

    public void setTraitsCles(String traitsCles) {
        TraitsCles = traitsCles;
    }
}
