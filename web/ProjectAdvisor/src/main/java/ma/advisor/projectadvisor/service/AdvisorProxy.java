package ma.advisor.projectadvisor.service;

import ma.advisor.projectadvisor.DTOs.ProfileResponse;
import ma.advisor.projectadvisor.config.ApiConfig;
import ma.advisor.projectadvisor.config.RestTemplateConfig;
import ma.advisor.projectadvisor.DTOs.Profile;
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
        System.out.println(apiConfig.getURL() +"/profile" + "  POST");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .postForObject(apiConfig.getURL() +"/profile",profile,String.class);
    }

    public ProfileResponse getValeursProfile() {
        System.out.println(apiConfig.getURL() +"/profile" + "   GET");
        return restTemplate.restTemplate(new RestTemplateBuilder())
                .getForObject(apiConfig.getURL() +"/profile",ProfileResponse.class);
    }
}
