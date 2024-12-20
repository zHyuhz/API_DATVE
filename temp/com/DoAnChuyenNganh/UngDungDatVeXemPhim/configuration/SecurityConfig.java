package com.DoAnChuyenNganh.UngDungDatVeXemPhim.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



//https://docs.spring.io/spring-security/reference/servlet/architecture.html --> Tài liệu

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

//	private final String[] PUBLIC_ENPOINTS = { "/accounts/register", "/auth/login", "/auth/introspect" };
//	@Value("${jwt.signerKey}")
//	private String signerKey;
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeHttpRequests(
//				t -> t.requestMatchers(HttpMethod.POST, PUBLIC_ENPOINTS).permitAll()
//				.anyRequest()	.authenticated());
//
//		httpSecurity.oauth2ResourceServer(oauth2 -> 
//							oauth2.jwt(jwtConfig -> jwtConfig.decoder(jwtDecoder())
//									.jwtAuthenticationConverter(authenticationConverter()))
//							);
//
//		httpSecurity.csrf(t -> t.disable());
//		return httpSecurity.build();
//	}
//	
//	//Custom JWT
//	@Bean
//	JwtAuthenticationConverter authenticationConverter () {
//		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//		
//		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
//		authenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//		
//		return authenticationConverter;
//	}
//	
//	@Bean
//	JwtDecoder jwtDecoder() {
//		SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//		return NimbusJwtDecoder
//				.withSecretKey(secretKeySpec)
//				.macAlgorithm(MacAlgorithm.HS512)
//				.build();
//	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);	
	}
}
