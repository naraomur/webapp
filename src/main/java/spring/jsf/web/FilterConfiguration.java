package spring.jsf.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.jsf.web.filter.UserFilter;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean userFilter() {
        FilterRegistrationBean registration =
                new FilterRegistrationBean();
        registration.setFilter(new UserFilter());
        registration.addServletNames("Faces Servlet");
        registration.addUrlPatterns("/faces/Html/admin/*", "/facesHtml/secured/*");
        return registration;
    }
}
