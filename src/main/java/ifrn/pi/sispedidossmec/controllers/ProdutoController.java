package ifrn.pi.sispedidossmec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.sispedidossmec.models.Produto;
import ifrn.pi.sispedidossmec.repositories.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository pr;

	@RequestMapping("/produtos/form")
	public String form() {
		return "formProduto";
	}
	
	@PostMapping("/produtos")
	public String adicionar(Produto produto) {
		pr.save(produto);
		return "produto-adicionado";
	}
}
