package br.com.zupacademy.mercadolivre.model.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.mercadolivre.model.Imagem;
import br.com.zupacademy.mercadolivre.model.Opiniao;
import br.com.zupacademy.mercadolivre.model.Pergunta;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.repository.ImagemRepository;
import br.com.zupacademy.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.mercadolivre.repository.PerguntaRepository;

public class ProdutoDetalhadoDto {
	private String nome;
	private BigDecimal valor;
	private Long quantidade;
	private Map<String, String> caracteristicas;
	private String descricao;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime dataCadastro;

	private List<ImagemDto> imagens;
	private String mediaNotas;
	private Long totalNotas;
	private List<OpiniaoDto> opinioes;
	private List<PerguntaDto> perguntas;
	
	// buscar opiniao, imagem, pergunta por produto
	
	public ProdutoDetalhadoDto(Produto produto, 
			PerguntaRepository perguntaRepository, 
			OpiniaoRepository opiniaoRepository, 
			ImagemRepository imagemRepository) {		
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.caracteristicas = produto.getCaracteristicas();
		this.descricao = produto.getDescricao();
		this.dataCadastro = produto.getDataCadastro();		

		List<Imagem> imagensDoBD = imagemRepository.findByProduto_nome(nome); 
		this.imagens = imagensDoBD.stream().map(ImagemDto::new).collect(Collectors.toList());
		
		List<Opiniao> opinioesDoBD = opiniaoRepository.findByProduto_nome(nome); 
		this.opinioes = opinioesDoBD.stream().map(OpiniaoDto::new).collect(Collectors.toList());
		
		List<Pergunta> perguntasDoBD = perguntaRepository.findByProduto_nome(nome); 
		this.perguntas = perguntasDoBD.stream().map(PerguntaDto::new).collect(Collectors.toList());
		
		DecimalFormat df = new DecimalFormat("#.#");
		this.mediaNotas = df.format(opinioesDoBD.stream().mapToInt(opiniao -> opiniao.getNota()).average().orElseGet(() -> 0.0));
		this.totalNotas = (long) opinioes.size();
	}

	public static ProdutoDetalhadoDto converter(Produto produto, 
			PerguntaRepository perguntaRepository, 
			OpiniaoRepository opiniaoRepository, 
			ImagemRepository imagemRepository) {
		return new ProdutoDetalhadoDto(produto, perguntaRepository, opiniaoRepository, imagemRepository);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Map<String, String> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public List<ImagemDto> getImagens() {
		return imagens;
	}

	public String getMediaNotas() {
		return mediaNotas;
	}

	public Long getTotalNotas() {
		return totalNotas;
	}

	public List<OpiniaoDto> getOpinioes() {
		return opinioes;
	}

	public List<PerguntaDto> getPerguntas() {
		return perguntas;
	}

}
