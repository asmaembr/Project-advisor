package ma.advisor.projectadvisor.DTOs;

import java.util.List;

public record ProfileResponse(
        List<String> valeurs_education,
        List<String> valeurs_trait
) {
}
