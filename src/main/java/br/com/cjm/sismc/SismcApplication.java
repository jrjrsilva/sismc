package br.com.cjm.sismc;




import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cjm.sismc.domain.Categoria;
import br.com.cjm.sismc.domain.Cidade;
import br.com.cjm.sismc.domain.Cliente;
import br.com.cjm.sismc.domain.Endereco;
import br.com.cjm.sismc.domain.Estado;
import br.com.cjm.sismc.domain.ItemPedido;
import br.com.cjm.sismc.domain.Pagamento;
import br.com.cjm.sismc.domain.PagamentoComBoleto;
import br.com.cjm.sismc.domain.PagamentoComCartao;
import br.com.cjm.sismc.domain.Pedido;
import br.com.cjm.sismc.domain.Produto;
import br.com.cjm.sismc.domain.enums.EstadoPagamento;
import br.com.cjm.sismc.domain.enums.TipoCliente;
import br.com.cjm.sismc.repositories.CategoriaRepository;
import br.com.cjm.sismc.repositories.CidadeRepository;
import br.com.cjm.sismc.repositories.ClienteRepository;
import br.com.cjm.sismc.repositories.EnderecoRepository;
import br.com.cjm.sismc.repositories.EstadoRepository;
import br.com.cjm.sismc.repositories.ItemPedidoRepository;
import br.com.cjm.sismc.repositories.PagamentoRepository;
import br.com.cjm.sismc.repositories.PedidoRepository;
import br.com.cjm.sismc.repositories.ProdutoRepository;

@SpringBootApplication
public class SismcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SismcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria jose","mariajose@gmail.com","12345678921",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("13269856","96328754"));
		
		Endereco e1 = new Endereco(null,"Rua flores","300","Apto 303","Jardim","20151968",cli1,c1);
		Endereco e2 = new Endereco(null,"Av St Louis","390","Apto 215","Centro","26152964",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		Pedido ped1 = new Pedido(null,sdf.parse("20/12/2018 18:20"), cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("20/12/2018 19:20"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2, sdf.parse("21/12/2018 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}

