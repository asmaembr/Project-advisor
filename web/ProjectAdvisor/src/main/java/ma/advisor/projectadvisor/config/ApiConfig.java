package ma.advisor.projectadvisor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "advisor-api")
@Data
public class ApiConfig {
    private String url;

}
