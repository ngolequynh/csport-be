package sportstracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.KeyLengthException;
import sportstracker.common.property.SecurityProperties;
import sportstracker.security.JwtAuthenticationProvider;
import sportstracker.security.JwtService;
import sportstracker.security.SpectreOAuth2UserService;
import sportstracker.security.SpectreOidcUserService;
import sportstracker.security.AuthenticationSuccessHandler;
import sportstracker.security.OAuth2AuthenticationFailureHandler;
import sportstracker.security.OAuth2AuthenticationSuccessHandler;
import sportstracker.service.AccountService;
import sportstracker.service.ProfileService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Bean provider
 *
 * @author Chuc Ba Hieu
 */
@Configuration
public class BeanConfig {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(JwtService.class)
    public JwtService jwtService(SecurityProperties properties) throws KeyLengthException {
        return new JwtService(properties.getJwt().getSecret());
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
    public AuthenticationSuccessHandler authenticationSuccessHandler(ObjectMapper objectMapper, JwtService jwtService, SecurityProperties properties) {
        return new AuthenticationSuccessHandler(objectMapper, jwtService, properties);
    }

    @Bean
    @ConditionalOnMissingBean(OAuth2AuthenticationSuccessHandler.class)
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler(SecurityProperties properties, JwtService jwtService) {
        return new OAuth2AuthenticationSuccessHandler(properties, jwtService);
    }

    @Bean
    @ConditionalOnMissingBean(OAuth2AuthenticationFailureHandler.class)
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler();
    }

    @Bean
    @ConditionalOnMissingBean(SpectreOAuth2UserService.class)
    public SpectreOAuth2UserService oAuth2UserService(AccountService accountService, ProfileService profileService) {
        return new SpectreOAuth2UserService(accountService, profileService);
    }

    @Bean
    @ConditionalOnMissingBean(SpectreOidcUserService.class)
    public SpectreOidcUserService oidcUserService(SpectreOAuth2UserService spectreOAuth2UserService) {
        return new SpectreOidcUserService(spectreOAuth2UserService);
    }

    @Bean
    @ConditionalOnMissingBean(JwtAuthenticationProvider.class)
    public JwtAuthenticationProvider jwtAuthenticationProvider(JwtService jwtService, AccountService accountService) {
        return new JwtAuthenticationProvider(jwtService, accountService);
    }

    @Bean
    public ClassLoaderTemplateResolver templateResolver() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("templates/example/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());

        return templateEngine;
    }

}
