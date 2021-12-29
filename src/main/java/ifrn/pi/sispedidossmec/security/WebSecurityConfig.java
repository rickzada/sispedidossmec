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
		
		//Acessos do usuário com perfil de Chefe de Almoxarifado
		.antMatchers(HttpMethod.GET, "/produtos/form").hasRole("ALM")
		.antMatchers(HttpMethod.POST, "/produtos").hasRole("ALM")
		.antMatchers(HttpMethod.GET, "/pedidos/formBaixaPedido").hasRole("ALM")
		.antMatchers(HttpMethod.GET, "/pedidos/baixa/{idPedBaixa}").hasRole("ALM")
		.antMatchers(HttpMethod.POST, "pedidos/baixa/{idPedBaixa}").hasRole("ALM")
		
		//Acessos do usuário com perfil de Chefe de Diretor
		.antMatchers(HttpMethod.GET, "/pedidos/form").hasRole("DIR")
		.antMatchers(HttpMethod.POST, "/pedidos/").hasRole("DIR")
		.antMatchers(HttpMethod.GET, "pedidos/listar").hasRole("DIR")
		.antMatchers(HttpMethod.GET, "pedidos/detalhes/{idPed}").hasRole("DIR")
		.antMatchers(HttpMethod.GET, "pedidos/{id}").hasRole("DIR")
		.antMatchers(HttpMethod.POST, "pedidos/{idPedido}").hasRole("DIR")
		
		
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
