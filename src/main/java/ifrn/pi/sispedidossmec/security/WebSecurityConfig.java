package ifrn.pi.sispedidossmec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/produtos/form").hasAnyRole("ALM","SEC")
		.antMatchers(HttpMethod.POST, "/produtos").hasAnyRole("ALM","SEC")
		.antMatchers(HttpMethod.GET, "/pedidos/formBaixaPedido").hasAnyRole("ALM","SEC")
		.antMatchers(HttpMethod.GET, "/pedidos/baixa/{idPedBaixa}").hasAnyRole("ALM","SEC")
		.antMatchers(HttpMethod.POST, "pedidos/baixa/{idPedBaixa}").hasAnyRole("ALM","SEC")
		
		.antMatchers(HttpMethod.GET, "/pedidos/form").hasAnyRole("DIR","SEC")
		.antMatchers(HttpMethod.POST, "/pedidos/").hasAnyRole("DIR","SEC")
		.antMatchers(HttpMethod.GET, "/pedidos/listar").hasAnyRole("DIR","SEC")
		.antMatchers(HttpMethod.GET, "pedidos/detalhes/{idPed}").hasAnyRole("DIR","SEC")
		.antMatchers(HttpMethod.GET, "pedidos/{id}").hasAnyRole("DIR","SEC")
		.antMatchers(HttpMethod.POST, "pedidos/{idPedido}").hasAnyRole("DIR","SEC")

		
		.antMatchers(HttpMethod.GET, "/usuarios/form").hasRole("SEC")
		.antMatchers(HttpMethod.POST, "/usuarios").hasRole("SEC")
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

}
