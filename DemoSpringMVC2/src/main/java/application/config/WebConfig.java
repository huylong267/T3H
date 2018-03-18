package application.config;

import application.data.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Bean  //map với  autowired
    public ProductService getProductService(){
        return new ProductService();
    }

    private  static final Logger LOGGER = LogManager.getLogger(WebConfig.class);
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root:}")
    private  String templatesRoot;
    @Bean
    public ITemplateResolver defaultTemplateResolver(){
        LOGGER.debug("run defaultTemplateResoler");
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        return resolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**",
                                                "/static/img/**",
                                                "/static/css/**",
                                                "/static/js/**"
                ).addResourceLocations("classpath:/META-INF/resources/webjars/",
                                        "classpath:/static/img/",
                                        "classpath:/static/css/",
                                        "classpath:/static/js/"
        );
    }
}
