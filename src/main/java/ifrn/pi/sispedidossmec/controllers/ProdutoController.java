package ifrn.pi.sispedidossmec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutoController {

	@RequestMapping("/produtos/form")
	public String form() {
		return "formProduto";
	}
	
}
