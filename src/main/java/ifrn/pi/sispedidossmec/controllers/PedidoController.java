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

	// Método que retorna o formulário de cadastro de novo pedido
	@RequestMapping("/pedidos/form")
	public String form() {
		return "pedidos/formPedido";
	}

	// Método que recebe um pedido e guarda no BD
	@PostMapping("/pedidos")
	public String adicionar(Pedido pedido) {
		pedido.setStatus("AGUARDANDO CONFIRMAÇÃO");
		pr.save(pedido);
		return "pedidos/pedido-adicionado";
	}

	// Método que lista todos os pedidos
	@GetMapping("pedidos/listar")
	public ModelAndView listar() {
		List<Pedido> pedidos = pr.findAll();
		ModelAndView mv = new ModelAndView("pedidos/listar");
		mv.addObject("pedidos", pedidos);
		return mv;
	}

	// Método que detalha as informações do pedido
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

	// Método que fornece a página para adicionar itens ao pedido
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

	// Método que associa os itens ao pedido
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

	//Método que retorna a os pedidos pendentes de baixa
	@RequestMapping("/pedidos/formBaixaPedido")
	public ModelAndView pedidosAConfirmar() {
		List<Pedido> pedidos = pr.findByStatusEquals("AGUARDANDO CONFIRMAÇÃO");
		ModelAndView mv = new ModelAndView("pedidos/pedidos-a-confirmar");
		mv.addObject("pedidos", pedidos);
		return mv;
	}
	
	//Método que retorna as informações de um pedido para dar baixa
	@GetMapping("pedidos/baixa/{idPedBaixa}")
	public ModelAndView darBaixaPedido(@PathVariable Long idPedBaixa) {
		ModelAndView mv = new ModelAndView();
		Optional<Pedido> opt = pr.findById(idPedBaixa);
		
		if (opt.isEmpty()) {
			mv.setViewName("redirect:/pedidos/pedidos-a-confirmar");
			return mv;
		}
		
		mv.setViewName("pedidos/baixa-pedido");
		Pedido pedido = opt.get();
		
		mv.addObject("pedido", pedido);
		return mv;
	}
	
	//Método que altera o status do pedido efetivando a baixa no pedido
	@PostMapping("pedidos/baixa/{idPedBaixa}")
	public String darBaixaPedido(@PathVariable Long idPedBaixa, Pedido pedido) {
		Optional<Pedido> opt = pr.findById(idPedBaixa);
		
		if (opt.isEmpty()) {
			return "redirect:/pedidos/formBaixaPedido";
		}
		
		pedido = opt.get();
		pedido.setStatus("BAIXA NO PEDIDO");
		pr.save(pedido);
		
		return "redirect:/pedidos/formBaixaPedido";
	}

}
