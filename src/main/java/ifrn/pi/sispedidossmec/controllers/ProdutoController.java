package ifrn.pi.sispedidossmec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.sispedidossmec.models.Produto;
import ifrn.pi.sispedidossmec.repositories.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository pr;

	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/formProduto";
	}
	
	@PostMapping("/produtos")
	public String adicionar(Produto produto) {
		pr.save(produto);
		return "redirect:/produtos/listar";
	}
	
	@GetMapping("produtos/listar")
	public ModelAndView listar() {
		List<Produto> produtos = pr.findAll();
		ModelAndView mv = new ModelAndView("produtos/listar");
		mv.addObject("produtos", produtos);
		return mv;
	}
}
