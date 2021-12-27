package ifrn.pi.sispedidossmec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.sispedidossmec.models.ItemPedido;
import ifrn.pi.sispedidossmec.models.Pedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
	
	List<ItemPedido> findByPedido(Pedido pedido);

}
