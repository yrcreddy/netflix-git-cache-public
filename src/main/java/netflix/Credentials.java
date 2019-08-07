package netflix;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties
@Accessors(fluent = true)
@Data
public class Credentials {
    @Value("${github.username}")
    private String githubUsername;

    @Value("${github.password}")
    private String githubPassword;

    @Value("${github.token}")
    private String githubOuth2Token;

}

