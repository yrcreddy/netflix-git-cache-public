package netflix;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties
@Accessors(fluent = true)
@Data
public class CacheConfig {
    @Value("${cache.expireAfterWrite.minutes}")
    private Integer expireAfterWrite;

    @Value("${cache.maximumSize}")
    private Integer maximumSize;

}

