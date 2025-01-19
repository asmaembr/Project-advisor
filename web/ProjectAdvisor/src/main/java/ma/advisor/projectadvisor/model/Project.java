package ma.advisor.projectadvisor.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    //top 500 study information
    private String ville;
    private Integer nombre_relations;
    private Integer tours_financement;
    private Long capitale_fonds;
    private String categorie;
    private Integer investisseurs_providentiels;
    private Integer nombre_participants;
    private String isTop500;

    //profititablity study information
    private String region;
    private Double rnd;
    private Double marketing;
    private Double administration;
    private String profit;

    //mapping with entrepreneur
    @ManyToOne
    private Entrepreneur entrepreneur;
}