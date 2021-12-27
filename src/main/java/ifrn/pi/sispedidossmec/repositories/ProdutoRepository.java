package ifrn.pi.sispedidossmec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.sispedidossmec.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
