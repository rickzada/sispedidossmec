package ifrn.pi.sispedidossmec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.sispedidossmec.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByStatusEquals(String status);

}
