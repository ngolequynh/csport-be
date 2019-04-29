package sportstracker;

import sportstracker.common.property.FileStorageProperties;
import sportstracker.common.property.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({
        SecurityProperties.class,
        FileStorageProperties.class
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
