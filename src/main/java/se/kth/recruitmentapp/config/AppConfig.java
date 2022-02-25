package se.kth.recruitmentapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    /**
     * Configuration of where to find message and validation properties.
     *
     * Specifies the paths to Message and Validation message properties as base names.
     * @return messageSource, holds the paths to Message and ValidationMessage properties, specified as base names.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/i18n/Messages", "classpath:/i18n/ValidationMessages");
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

    /**
     * Register the i18n interceptor.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Creates a servlet for locale management.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        String[] allowedMethodsForLocale = { "GET", "POST"};

        LocaleChangeInterceptor i18nBean = new LocaleChangeInterceptor();
        i18nBean.setParamName("lang");
        i18nBean.setHttpMethods(allowedMethodsForLocale);
        return i18nBean;
    }

    /**
     * Stores the user's current locale in the session object.
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
}
