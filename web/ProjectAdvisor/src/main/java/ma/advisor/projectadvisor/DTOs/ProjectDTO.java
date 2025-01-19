package ma.advisor.projectadvisor.DTOs;


import lombok.Data;

@Data
public class ProjectDTO {
    private Integer id;
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

}
