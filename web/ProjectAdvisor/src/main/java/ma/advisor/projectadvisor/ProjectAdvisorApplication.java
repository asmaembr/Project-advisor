package ma.advisor.projectadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ProjectAdvisorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAdvisorApplication.class, args);
    }

}
