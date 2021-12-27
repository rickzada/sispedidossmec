package ifrn.pi.sispedidossmec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.sispedidossmec.models.Pedido;
import ifrn.pi.sispedidossmec.repositories.PedidoRepository;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoRepository pr;

	@RequestMapping("/pedidos/form")
	public String form() {
		return "pedidos/formPedido";
	}
	
	@PostMapping("/pedidos")
	public String adicionar(Pedido pedido) {
		pr.save(pedido);
		return "pedidos/pedido-adicionado";
	}
	
}
