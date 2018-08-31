package online.nwen;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
@EnableWebMvc
public class Web implements WebMvcConfigurer {
    /**
     * Register the static resource.
     *
     * @param registry The static resource registry.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/")
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    /**
     * Redirect the ui request to the main html(index.html) of client side.
     * After redirect the angular engine will be loaded and running.
     *
     * @param registry The view registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //Redirect the root to index.html
        registry.addRedirectViewController("/", "/ui/index.html");
        //Redirect the "/ui/#/**" to index.html
        registry.addRedirectViewController("/ui/", "/ui/index.html");
    }
}
