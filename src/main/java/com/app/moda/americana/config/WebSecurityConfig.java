package com.app.moda.americana.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

import com.app.moda.americana.repository.IUserRepository;
import com.app.moda.americana.service.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
     
	private final IUserRepository userRepository;

    @Autowired
    private CustomSuccesHandler succesHandler;
	
	@Bean
	  public UserDetailsService userDetailsService() {
	    return new CustomUserDetailsService(userRepository);
	  }
    
    @Autowired
    public WebSecurityConfig(IUserRepository userRepository) {
      this.userRepository = userRepository;
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
 
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
     
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // No se si aquí esta el error es probable que tenga algo mal aquí o en la clase CustomUserDetailsImpl o por ahí
        http.authorizeRequests()
                //.antMatchers("/users").fullyAuthenticated()
                //.antMatchers("/", "/form_contact", "/login", "forgot_password", "register").permitAll()
                    .antMatchers(
                            "/users", "/dash", "/product/all/pacas","/product/modify/paca/", "/product/modify/paca/**", "/product/new/paca", "/product/delete/paca/**",
                            "/product/save/paca", "/product/all/unitary", "/product/new/unitary", "/product/save/unitary", "/product/modify/unitary/**", "/product/delete/unitary/**",
                            "/category", "/new_category", "addCategory", "/category/edit/**", "/category/edit/**", "/category/**", "/users", "/user/update/**",/* "/user/delete/**",*/ "/user/update/**",
                            "/provider/all", "/provider/new", "/provider/modify/**", "/provider/save", "/provider/delete/**")
                    .hasRole("ADMIN")
                    .anyRequest().permitAll()
                .and()
                    .formLogin().successHandler(succesHandler)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("/login").loginProcessingUrl("/login_post")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/404");
    	/*
        .and()
        .sessionManagement()
        .invalidSessionUrl("/login?invalid-session=true")
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true)
        .expiredUrl("/login?invalid-session=true");
        */
        return http.build();
    }
    
    @Bean
    public LocaleResolver localeResolver() {
    	SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    	localeResolver.setDefaultLocale(new Locale("es", "ES"));
    	return localeResolver;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
    	LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
    	localeInterceptor.setParamName("lang");
    	return localeInterceptor;
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/Perfiles/**").addResourceLocations("file:/C:/Desarrollo/Java-Projects/Fotos-ModaAmericana/Perfiles/");
    }

    
}
