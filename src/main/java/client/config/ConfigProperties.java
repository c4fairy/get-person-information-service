package client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "my")
public class ConfigProperties {

    /**
     * url адрес сервера
     */
    private String url;
}
