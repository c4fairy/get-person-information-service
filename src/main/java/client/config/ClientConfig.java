package client.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ClientConfig {
    /**
     * url адрес сервера
     */

    private String url;
}
