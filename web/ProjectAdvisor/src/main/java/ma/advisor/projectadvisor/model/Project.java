package ma.advisor.projectadvisor.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //top 500 study information
    private String ville;
    private Integer nombre_relations;
    private Integer tours_financement;
    private Long capitale_fonds;
    private String categorie;
    private String investisseurs_providentiels;
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

    @LastModifiedDate
    private LocalDateTime updatedDate;

}