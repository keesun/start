package whiteship.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import whiteship.domain.enumeration.UserRole;
import whiteship.service.AuthService;
import whiteship.service.UserDetailsImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keeun Baik
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "authService")
    private UserDetailsService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permitAllUrls = new String[]{"/", "/users/signupform",
                "/users/loginform", "/users/login", "/users/loginfailure",
                "/users/logout", "/users/signup", "/test/**"};

        http.formLogin()
                .loginPage("/users/loginform")
                .loginProcessingUrl("/users/login")
                .failureUrl("/users/loginfailure")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
            .rememberMe()
                .key("yobi")
                .tokenValiditySeconds(1296000)
                .userDetailsService(userDetailsService())
                .and()
            .authorizeRequests()
                .antMatchers(permitAllUrls).permitAll()
                .anyRequest().hasRole(UserRole.USER.name());

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter> voters = new ArrayList<>();
        voters.add(new RoleHierarchyVoter(roleHierarchy()));
        voters.add(new AuthenticatedVoter());
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(expressionHandler());
        voters.add(webExpressionVoter);
        return new AffirmativeBased(voters);
    }

    @Bean
    public SecurityExpressionHandler<FilterInvocation> expressionHandler() {
        DefaultWebSecurityExpressionHandler bean = new DefaultWebSecurityExpressionHandler();
        bean.setRoleHierarchy(roleHierarchy());
        return bean;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl bean = new RoleHierarchyImpl();
        bean.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return bean;
    }

}
