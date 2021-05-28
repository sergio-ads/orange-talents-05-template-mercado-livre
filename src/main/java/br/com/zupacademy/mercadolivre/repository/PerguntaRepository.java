package br.com.zupacademy.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mercadolivre.model.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

}
