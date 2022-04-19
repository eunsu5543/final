package project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	 @Override
	 public void addCorsMappings(CorsRegistry registry) {
	   registry.addMapping("/**")
	       .allowedOrigins("http://localhost:3000")
	       .allowedMethods("*")
	       .maxAge(3000);
	 }
  
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
		return commonsMultipartResolver;
	}
	    
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    	MustacheViewResolver resolver = new MustacheViewResolver();
    	resolver.setCharset("UTF-8");
    	resolver.setContentType("text/html;charset=utf-8");
    	resolver.setPrefix("classpath:/templates/");
    	resolver.setSuffix(".html");
    	registry.viewResolver(resolver);
    }
  
}
