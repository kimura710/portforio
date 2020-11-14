package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// パスワードの暗号化にBCrypt使用
		return new BCryptPasswordEncoder();
	}
	// セキュリティ設定を無視するパスを指定
	@Override
	public void configure(WebSecurity web)throws Exception{
		web.ignoring().antMatchers("/css/**","/webjars/**");
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
	
		http.authorizeRequests()
		// 「login」をアクセス可能に
		    .antMatchers("/login").permitAll()
		    .anyRequest().authenticated()
		    .and()
		 .formLogin()
		 // login時のURL指定
		    .loginPage("/login")
		 // 認証後にリダイレクトする場所指定   
		    .defaultSuccessUrl("/")
		    .and()
		 .logout()
		 // log outするときのURL指定
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    .and()
		 //　ブラウザを閉じて、また開いてもログインしたままにできる   
		 .rememberMe();   
		    
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.inMemoryAuthentication()
		/*
		 * ユーザに「kimura」「admin」「test」を用意した
		 * パスワードも設定
		 */
	     .withUser("kimura")
		    .password(passwordEncoder().encode("test111"))
		    .authorities("ROLE_ADMIN")
		    .and()
		 .withUser("admin")
		    .password(passwordEncoder().encode("password"))
		    .authorities("ROLE_ADMIN")
		    .and()
		 .withUser("test")
		    .password(passwordEncoder().encode("0000"))
		    .authorities("ROLE_ADMIN");
	}
}
