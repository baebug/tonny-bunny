package com.tonnybunny.config;


import com.tonnybunny.auth.jwt.filter.JwtAuthenticationFilter;
import com.tonnybunny.auth.jwt.filter.JwtAuthorizationFilter;
import com.tonnybunny.auth.jwt.service.JwtService;
import com.tonnybunny.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터 체인에 등록
@RequiredArgsConstructor
public class SecurityConfig {

	private final CorConfig config;
	private final JwtService jwtService;
	private final UserRepository userRepository;


	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() { // 비로그인 접근 가능한 경로
		return web -> web.ignoring()
			.antMatchers("/join", "/", "/home", "/refresh/**");
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable() // 토큰인증할거니까 csrf 막기
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않겠다
			// 스프링 시큐리티가 제공하는 세션 검증 필터를 사요하지 않겠다는 의미
			.and()
			.formLogin().disable() // formLogin 방식 사용 안함. json 방식으로 전달
			.httpBasic().disable() // Bearer 방식 사용 -> header에 authentication에 토큰을 넣어 전달하는 방식

			.apply(new MyCustomDsl())
			.and()

			.authorizeRequests()
			.antMatchers("/api/v1/user/**")
			.hasAuthority("USER") // 사실 우리는 이렇게 권한아직안나눠서 필요없긴 함. 나중에 보기
			.antMatchers("/api/v1/manager/**").hasAuthority("MANAGER")
			.antMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
			.anyRequest().permitAll()

			.and()
			.build();

	}


	public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(
				AuthenticationManager.class);

			http
				.addFilter(config.corsFilter())
				.addFilter(new JwtAuthenticationFilter(authenticationManager,
					jwtService)) // AuthenticationManager가 있어야 됨 (파라미터로)
				.addFilter(
					new JwtAuthorizationFilter(authenticationManager, userRepository, jwtService));

		}

	}

}
