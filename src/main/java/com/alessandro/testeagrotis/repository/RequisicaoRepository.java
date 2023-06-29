package com.alessandro.testeagrotis.repository;

import com.alessandro.testeagrotis.domain.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
}
