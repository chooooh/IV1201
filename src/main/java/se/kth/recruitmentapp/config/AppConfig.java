package se.kth.recruitmentapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableTransactionManagement
@EnableWebMvc
@Configuration
public class AppConfig implements WebMvcConfigurer {

    /**
     * Configuration of where to find message and validation properties.
     * Specifies the paths to Message and Validation message properties as base names.
     * @return messageSource, holds the paths to Message and ValidationMessage
     *                       properties, specified as base names.
     */
    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/i18n/Message");
        messageSource.addBasenames("classpath:/i18n/ValidationMessages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1800);
        return messageSource;
    }















}
