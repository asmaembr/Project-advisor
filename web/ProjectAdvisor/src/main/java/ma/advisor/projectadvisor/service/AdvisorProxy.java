package ma.advisor.projectadvisor.service;

import ma.advisor.projectadvisor.DTOs.ProfileResponse;
import ma.advisor.projectadvisor.DTOs.ProfitResponse;
import ma.advisor.projectadvisor.DTOs.Top500Response;
import ma.advisor.projectadvisor.config.ApiConfig;
import ma.advisor.projectadvisor.config.RestTemplateConfig;
import ma.advisor.projectadvisor.DTOs.Profile;
import ma.advisor.projectadvisor.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class AdvisorProxy {

    @Autowired
    private RestTemplateConfig restTemplate ;

    @Autowired
    private ApiConfig apiConfig;

    public String ProfilePrediction(Profile profile){
        System.out.println(apiConfig.getUrl() +"/profile" + "  POST");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .postForObject(apiConfig.getUrl() +"/profile",profile,String.class);
    }

    public ProfileResponse getValeursProfile() {
        System.out.println(apiConfig.getUrl() +"/profile" + "   GET");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .getForObject(apiConfig.getUrl() +"/profile",ProfileResponse.class);
    }

    public String ProfitPrediction(Project project){
        System.out.println(apiConfig.getUrl() +"/profit" + "  POST");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .postForObject(apiConfig.getUrl() +"/profit",project,String.class);
    }
    public ProfitResponse getValeursProfit() {
        System.out.println(apiConfig.getUrl() +"/profit" + "   GET");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .getForObject(apiConfig.getUrl() +"/profit", ProfitResponse.class);
    }
    public String Top500Prediction(Project projet){
        System.out.println(apiConfig.getUrl() +"/top500" + "  POST");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .postForObject(apiConfig.getUrl() +"/top500",projet,String.class);
    }

    public Top500Response getValeursTop500() {
        System.out.println(apiConfig.getUrl() +"/top500" + "   GET");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .getForObject(apiConfig.getUrl() +"/top500",Top500Response.class);
    }
}
