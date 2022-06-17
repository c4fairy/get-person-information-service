package client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Data
@ConfigurationProperties("url")
public class ConfigProperties {
    private String client;
    private String server;
}
