package ifrn.pi.sispedidossmec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.sispedidossmec.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLogin(String login);

}
