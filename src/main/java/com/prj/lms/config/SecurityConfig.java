package com.prj.lms.config;

import com.prj.lms.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*private static final String[] PUBLIC = new String[]{
            "/error", "/login", "/logout", "/register", "/api/registrations"
    };*/

    @Autowired
    //UserDetailServiceImpl userDetailService;
    MemberService memberService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.csrf().disable().httpBasic(); //rest api는 statelss하기 때문에 불필요..?
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); // csrf 토큰 지정

//        http.formLogin()
//                .loginPage("/members/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("email")
//                .failureUrl("/members/login/error")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
//                .logoutSuccessUrl("/");

//        http.csrf().disable().authorizeRequests() // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
//                .anyRequest().permitAll()
//                .and() // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and() // form 기반의 로그인에 대해 비활성화 한다.
//                .formLogin()
//                .disable();
//        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/hello").permitAll()
//                .antMatchers("/api/members/login").authenticated()
//                //.anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers(PUBLIC).permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/members/login")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logged-out")
//                .and()
//                .csrf().disable();

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole(ADMIN)")
                .antMatchers("/admin/**").access("hasRole(ADMIN)")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/members/login")
                .defaultSuccessUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Spring Security에서 인증은 AuthenticationManager를 통해 이루어지며
        // AuthenticationManagerBuilder가 todtjd.
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    /*@Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
    }*/
}
