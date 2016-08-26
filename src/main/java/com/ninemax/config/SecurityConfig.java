package com.ninemax.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.ninemax.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ninemax.service.UserServiceImpl;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserServiceImpl userDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Configuration
	@EnableAuthorizationServer
	protected static class Oauth2Config extends AuthorizationServerConfigurerAdapter {

		@Value("${security.oauth2.client.client-id}")
		private String clientId;
		
		@Value("${security.oauth2.client.client-secret}")
		private String clientSecret;
		
		@Autowired
		private AuthenticationManager auth;
		
		@Autowired
		private DataSource dataSource;
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Bean
		public JdbcTokenStore tokenStore() {
			return JdbcTokenStore(dataSource);
		}
		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.checkTokenAccess("isAuthenticated()");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			
			endpoints
				.authenticationManager(auth)
				.tokenServices(defaultTokenServices())
				.userDetailsService(userDetailsService)
				.addInterceptor(new HandlerInterceptorAdapter() {

					@Override
					public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
							throws Exception {
						return true;
					}

					@Override
					public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
							Object handler, Exception ex) throws Exception {
					}
					
				});
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients
				.inMemory()
				.withClient(clientId)
				.authorizedGrantTypes("password", "refresh_token")
				.authorities("ROLE_USER")
				.scopes("read", "write")
				.accessTokenValiditySeconds(3600)
				.secret(clientSecret);
		}
		
		@Autowired
		private ClientDetailsService clientDetailsService;
		
		@Primary
		@Bean
		public AuthorizationServerTokenServices defaultTokenServices() {
			final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
			defaultTokenServices.setTokenStore(tokenStore());
			defaultTokenServices.setClientDetailsService(clientDetailsService);
			defaultTokenServices.setTokenEnhancer(new CustomTokenEnhancer());
			defaultTokenServices.setSupportRefreshToken(true);
			return defaultTokenServices;
		}
		
		private class CustomTokenEnhancer implements TokenEnhancer {

			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				User user = (User) authentication.getPrincipal();
				final Map<String, Object> info = new HashMap<>();
				info.put("username", user.getName());
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
				return accessToken;
			}
			
		}
		
		@Configuration
		@EnableResourceServer
		protected static class ResourceServer extends ResourceServerConfigurerAdapter {
			@Override
			public void configure(HttpSecurity http) throws Exception {
				http
					.authorizeRequests()
					.antMatchers("/resources/**", "/user", "/index").permitAll()
					.anyRequest().authenticated()// 静态资源不拦截
					.and()
					.csrf()
					.disable();
			}
		}
		
		@Bean
		public JdbcTokenStore JdbcTokenStore(DataSource dataSource) {
			return new JdbcTokenStore(dataSource);
		}
	}

}
