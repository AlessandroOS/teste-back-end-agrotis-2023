package com.alessandro.testeagrotis.service;

import com.alessandro.testeagrotis.domain.Laboratorio;
import com.alessandro.testeagrotis.domain.Propriedade;
import com.alessandro.testeagrotis.domain.Requisicao;
import com.alessandro.testeagrotis.repository.LaboratorioRepository;
import com.alessandro.testeagrotis.repository.PropriedadeRepository;
import com.alessandro.testeagrotis.repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

@Service
public class DBService {

	@Autowired
	LaboratorioRepository laboratorioRepository;
	@Autowired
	PropriedadeRepository propriedadeRepository;
	@Autowired
	RequisicaoRepository requisicaoRepository;
	
	public void instanciaDB() {

		Propriedade p1 = new Propriedade(null, "Nome Fazenda 1");
		Propriedade p2 = new Propriedade(null, "Nome Fazenda 2");
		Propriedade p3 = new Propriedade(null, "Nome Fazenda 3");

		Laboratorio l1 = new Laboratorio(null, "Nome Laboratório 1");
		Laboratorio l2 = new Laboratorio(null, "Nome Laboratório 2");
		Laboratorio l3 = new Laboratorio(null, "Nome Laboratório 3");

		propriedadeRepository.saveAll(Arrays.asList(p1, p2, p3));
		laboratorioRepository.saveAll(Arrays.asList(l1, l2, l3));

		Requisicao req1 = new Requisicao(null,"Luke", Instant.parse("2020-08-22T15:21:22Z"), Instant.parse("2020-10-22T15:21:22Z"), p3, "XX.XXX.XXX/0001-XX", l2, "TESTE 1");
		Requisicao req2 = new Requisicao(null,"Leia", Instant.parse("2021-02-10T15:21:22Z"), Instant.parse("2021-03-15T15:21:22Z"), p1, "XX.XXX.XXX/0002-XX", l3, "TESTE 2");
		requisicaoRepository.saveAll(Arrays.asList(req1, req2));
	}

}
