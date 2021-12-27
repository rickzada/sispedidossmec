package ifrn.pi.sispedidossmec.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.sispedidossmec.models.ItemPedido;
import ifrn.pi.sispedidossmec.models.Pedido;
import ifrn.pi.sispedidossmec.repositories.ItemPedidoRepository;
import ifrn.pi.sispedidossmec.repositories.PedidoRepository;

@Controller
public class PedidoController {

	@Autowired
	private PedidoRepository pr;
	
	@Autowired
	private ItemPedidoRepository ipr;

	@RequestMapping("/pedidos/form")
	public String form() {
		return "pedidos/formPedido";
	}

	@PostMapping("/pedidos")
	public String adicionar(Pedido pedido) {
		pr.save(pedido);
		return "pedidos/pedido-adicionado";
	}

	@GetMapping("pedidos/listar")
	public ModelAndView listar() {
		List<Pedido> pedidos = pr.findAll();
		ModelAndView mv = new ModelAndView("pedidos/listar");
		mv.addObject("pedidos", pedidos);
		return mv;
	}
	
	@GetMapping("pedidos/detalhes/{idPed}")
	public ModelAndView detalharPedidos(@PathVariable Long idPed) {
		ModelAndView mv = new ModelAndView();
		Optional<Pedido> opt = pr.findById(idPed);

		if (opt.isEmpty()) {
			mv.setViewName("redirect:/pedidos/listar");
			return mv;
		}

		mv.setViewName("pedidos/detalhes");
		Pedido pedido = opt.get();

		List<ItemPedido> itensPedidos = ipr.findByPedido(pedido);
		
		mv.addObject("pedido", pedido);
		mv.addObject("itensPedidos", itensPedidos);
		return mv;
	}

	@GetMapping("pedidos/{id}")
	public ModelAndView adicionarItens(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<Pedido> opt = pr.findById(id);

		if (opt.isEmpty()) {
			mv.setViewName("redirect:/pedidos/listar");
			return mv;
		}

		mv.setViewName("pedidos/adicionarItens");
		Pedido pedido = opt.get();

		mv.addObject("pedido", pedido);
		return mv;
	}

	@PostMapping("pedidos/{idPedido}")
	public String adicionarItens(@PathVariable Long idPedido, ItemPedido item) {
		
		ModelAndView mv = new ModelAndView();

		System.out.println(item.getProduto().getNome());

		Optional<Pedido> opt = pr.findById(idPedido);
		
		if (opt.isEmpty()) {
			mv.setViewName("redirect:/pedidos/listar");
		}
		
		
		Pedido pedido = opt.get();
		item.setPedido(pedido);
		
		ipr.save(item);

		return "redirect:/pedidos/{idPedido}";
	}
}
