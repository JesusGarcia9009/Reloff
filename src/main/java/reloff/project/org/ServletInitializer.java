package reloff.project.org;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer
 * @author jesusgarciahernandez9009@gmail.com
 * @version 1.0 Creacion
 * @since Java 1.8
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReloffSecurityApplication.class);
    }

}