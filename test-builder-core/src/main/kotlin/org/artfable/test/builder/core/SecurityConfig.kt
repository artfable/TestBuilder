package org.artfable.test.builder.core

import org.artfable.test.builder.core.model.UserDetailsDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.encoding.ShaPasswordEncoder
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.servlet.http.HttpServletResponse

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableJpaRepositories
@EntityScan
@EnableTransactionManagement
@ComponentScan
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val userDetailsAuthenticationProvider: UserDetailsAuthenticationProvider? = null

    @Bean
    fun passwordEncoder(): ShaPasswordEncoder {
        return ShaPasswordEncoder()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(userDetailsAuthenticationProvider)
                .eraseCredentials(false)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        val entryPoints = LinkedHashMap<RequestMatcher, AuthenticationEntryPoint>()
        entryPoints.put(RequestHeaderRequestMatcher("X-Requested-With",
                "XMLHttpRequest"), HttpStatusEntryPoint(
                HttpStatus.UNAUTHORIZED))

        val defaultEntryPoint = DelegatingAuthenticationEntryPoint(
                entryPoints)

        defaultEntryPoint.setDefaultEntryPoint(Http401AuthenticationEntryPoint(LOGIN_PAGE))

        http.csrf()
                .disable()
//                .headers().frameOptions().disable().and()
                .authorizeRequests()
//                    .anyRequest().authenticated()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/login/").permitAll()
                .antMatchers("/api").authenticated()
                .and().exceptionHandling().authenticationEntryPoint(defaultEntryPoint)
                .and().formLogin()
                    .loginPage(LOGIN_PAGE)
                    .permitAll()
                    .failureHandler { request, response, exception ->
                        request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception)
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.message)
                    }
                    .successHandler { _, response, authentication ->
                        response.status = HttpServletResponse.SC_OK
                        response.writer.print((authentication.principal as UserDetailsDto).authorities)
                    }
//                    .successForwardUrl("/")
                .and().logout()
                    .logoutUrl("/api/logout/")
                    .logoutSuccessHandler { _, response, _ -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED) }
                    .invalidateHttpSession(true)
                .and().headers().cacheControl().disable()
                .and().cors()
//                .and().sessionManagement().sessionFixation().none()


    }
}

/**
 * @author artfable
 * 17.12.17
 */
private const val LOGIN_PAGE = "/api/login/"
