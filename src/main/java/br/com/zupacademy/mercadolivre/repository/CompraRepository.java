package br.com.zupacademy.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mercadolivre.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, String> {

}
