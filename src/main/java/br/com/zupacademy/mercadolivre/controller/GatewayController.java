package br.com.zupacademy.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mercadolivre.compartilhado.email.EnviaEmail;
import br.com.zupacademy.mercadolivre.compartilhado.service.NotaFiscal;
import br.com.zupacademy.mercadolivre.compartilhado.service.Ranking;
import br.com.zupacademy.mercadolivre.gateway.Gateway;
import br.com.zupacademy.mercadolivre.gateway.request.GatewayRequest;
import br.com.zupacademy.mercadolivre.gateway.request.PagseguroRequest;
import br.com.zupacademy.mercadolivre.gateway.request.PaypalRequest;
import br.com.zupacademy.mercadolivre.model.Compra;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.StatusTransacao;
import br.com.zupacademy.mercadolivre.model.Transacao;
import br.com.zupacademy.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.mercadolivre.repository.TransacaoRepository;

@RestController
public class GatewayController {
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private TransacaoRepository transacaoRepository;
	@Autowired
	private EnviaEmail enviaEmail;
	@Autowired
	private NotaFiscal notaFiscal;
	@Autowired
	private Ranking ranking;
	
	@PostMapping("/retorno-pagseguro/{idCompra}")
	public void retornoPagseguro(@PathVariable("idCompra") String idCompra, 
			@RequestBody @Valid PagseguroRequest request, UriComponentsBuilder uriComponentsBuilder) {
		processaTransacao(idCompra, request, uriComponentsBuilder);
	}
	
	@PostMapping("/retorno-paypal/{idCompra}")
	public void retornoPaypal(@PathVariable("idCompra") String idCompra, 
			@RequestBody @Valid PaypalRequest request, UriComponentsBuilder uriComponentsBuilder) {
		processaTransacao(idCompra, request, uriComponentsBuilder);
	}
	
	private void processaTransacao(String idCompra, GatewayRequest request, UriComponentsBuilder uriComponentsBuilder) {
		Compra compra = compraRepository.findById(idCompra)
				.orElseThrow(() -> new IllegalStateException("Essa compra não foi localizada"));
		Transacao transacao = request.toModel(compra);		
		compra.adicionaTransacao(transacao, transacaoRepository, compraRepository);

		Produto produto = compra.getProduto();		
		Gateway gateway = compra.getGateway();
		if(transacao.getStatus().equals(StatusTransacao.SUCESSO)) {
			enviaEmail.enviar(produto.getUsuario(), 
					"Seu produto: " +produto.getNome() +", quantidade: " +compra.getQuantidade() +" foi vendido");
			notaFiscal.processar(idCompra, compra.getComprador().getId());
			ranking.processar(idCompra, produto.getUsuario().getId());
		} else {
			enviaEmail.enviar(produto.getUsuario(), 
					"Houve um erro no pagamento do produto: " +produto.getNome() +", quantidade: " +compra.getQuantidade() 
					+". Favor tentar novamente, link: " +gateway.getRedirect(compra, uriComponentsBuilder));
		}
	}
}
