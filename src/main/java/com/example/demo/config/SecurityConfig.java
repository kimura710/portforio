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
		// �p�X���[�h�̈Í�����BCrypt�g�p
		return new BCryptPasswordEncoder();
	}
	// �Z�L�����e�B�ݒ�𖳎�����p�X���w��
	@Override
	public void configure(WebSecurity web)throws Exception{
		web.ignoring().antMatchers("/css/**","/webjars/**");
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
	
		http.authorizeRequests()
		// �ulogin�v���A�N�Z�X�\��
		    .antMatchers("/login").permitAll()
		    .anyRequest().authenticated()
		    .and()
		 .formLogin()
		 // login����URL�w��
		    .loginPage("/login")
		 // �F�،�Ƀ��_�C���N�g����ꏊ�w��   
		    .defaultSuccessUrl("/")
		    .and()
		 .logout()
		 // log out����Ƃ���URL�w��
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    .and()
		 //�@�u���E�U����āA�܂��J���Ă����O�C�������܂܂ɂł���   
		 .rememberMe();   
		    
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.inMemoryAuthentication()
		/*
		 * ���[�U�Ɂukimura�v�uadmin�v�utest�v��p�ӂ���
		 * �p�X���[�h���ݒ�
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
