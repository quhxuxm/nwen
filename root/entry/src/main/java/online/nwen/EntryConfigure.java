package online.nwen;

import online.nwen.entry.interceptor.InitializeAuthenticatedAuthorDetailHolderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EntryConfigure implements WebMvcConfigurer {
    private final InitializeAuthenticatedAuthorDetailHolderInterceptor initializeAuthenticatedAuthorDetailHolderInterceptor;

    @Autowired
    public EntryConfigure(
            InitializeAuthenticatedAuthorDetailHolderInterceptor initializeAuthenticatedAuthorDetailHolderInterceptor) {
        this.initializeAuthenticatedAuthorDetailHolderInterceptor = initializeAuthenticatedAuthorDetailHolderInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                this.initializeAuthenticatedAuthorDetailHolderInterceptor);
    }
}
