package ma.advisor.projectadvisor.DTOs;

import java.util.List;

public record Top500Response(List<String> valeurs_ville, List<String> valeurs_categorie) {
}
