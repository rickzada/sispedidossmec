package ifrn.pi.sispedidossmec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.sispedidossmec.models.Produto;

@Controller
public class ProdutoController {

	@RequestMapping("/produtos/form")
	public String form() {
		return "formProduto";
	}
	
	@PostMapping("/produtos")
	public String adicionar(Produto produto) {
		return "produto-adicionado";
	}
}
