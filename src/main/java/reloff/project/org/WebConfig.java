package reloff.project.org;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@SuppressWarnings("deprecation")
public class WebConfig extends WebMvcConfigurerAdapter  {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");

		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");

		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		
		//
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

   		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		
	}
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    
}
