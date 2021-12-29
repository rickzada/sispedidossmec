package ifrn.pi.sispedidossmec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.sispedidossmec.models.Usuario;
import ifrn.pi.sispedidossmec.repositories.RoleRepository;
import ifrn.pi.sispedidossmec.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private RoleRepository rr;
	
	@RequestMapping("/usuarios/form")
	public String formCadUsuario() {
		return "/usuarios/formUsuario";
	}
	
	@PostMapping("/usuarios")
	public String adicionar(Usuario usuario) {
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		rr.save(usuario.getRoles().get(0));
		ur.save(usuario);
		
		return "redirect:/usuarios/form";
	}
	
}
