package web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The Class WebSecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	/** The b crypt password encoder. */
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/** The data source. */
	@Autowired
    private DataSource dataSource;

    /** The users query. */
    @Value("${spring.queries.users-query}")
    private String usersQuery;

    /** The roles query. */
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
    
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	
            .authorizeRequests()
            .antMatchers("/","/login","/registration").permitAll()
            .antMatchers("/admin/**").hasAuthority("ADMIN")
            .antMatchers("/ca/**").hasAnyAuthority("ADMIN","CA")
            .antMatchers("/navbar","/chore","groceries","disputes").hasAnyAuthority("RESIDENT","CA","ADMIN").anyRequest()
            .authenticated().and().csrf().disable().formLogin()
            .loginPage("/login").failureUrl("/login?error=true")
            .defaultSuccessUrl("/navbar")
            .usernameParameter("netID")
            .passwordParameter("password")
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/").and().exceptionHandling()
            .accessDeniedPage("/access-denied");
    }

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
