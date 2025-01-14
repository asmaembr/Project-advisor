package ma.advisor.projectadvisor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //top 500 study information
    private String Ville;
    private  Integer Nombre_relations;
    private Integer Tours_financement;
    private Long Capitale_fonds;
    private String Categorie;
    private Integer Investisseurs_providentiels;
    private Integer Nombre_participants;
    private Boolean isTop500;

    //profititablity study information
    private String Region;
    private Double R_D;
    private Double Marketing;
    private Double Administration;
    private String Profit;

}
