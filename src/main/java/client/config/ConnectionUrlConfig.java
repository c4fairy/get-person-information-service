package client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("url")
public class ConnectionUrlConfig {
    private String client;
    private String server;
}
