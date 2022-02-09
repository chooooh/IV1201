package se.kth.recruitmentapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    /**
     * Configuration of where to find message and validation properties.
     *
     * @return The resolved message.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/i18n/Message", "classpath:/i18n/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Defining the LocalValidatorFactoryBean, so that custom messages may be used.
     *
     * @return The customizes message
     */
     @Bean
     public Validator getValidator() {
         LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
         bean.setValidationMessageSource(messageSource());
         return bean;
     }
}
