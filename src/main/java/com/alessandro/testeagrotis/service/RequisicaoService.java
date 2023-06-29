package com.alessandro.testeagrotis.service;

import com.alessandro.testeagrotis.domain.Requisicao;
import com.alessandro.testeagrotis.repository.RequisicaoRepository;
import com.alessandro.testeagrotis.service.exceptions.DatabaseException;
import com.alessandro.testeagrotis.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository repository;

    public List<Requisicao> findAll() {
        return repository.findAll();
    }

    public Requisicao findById(Long id) {
        Optional<Requisicao> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(" Não encontrado o ID de número: " + id ));
    }

    public Requisicao insert(Requisicao obj) {

        Instant dataInicial = obj.getDataInicial();
        Instant dataFinal = obj.getDataFinal();

        if(Objects.isNull(dataInicial)) {
            throw new IllegalArgumentException("Data Inicial obrigatoria");
        } else if (Objects.isNull(dataFinal)) {
            throw new IllegalArgumentException("Data Final obrigatoria");
        } else if (Objects.isNull(obj.getLaboratorio())) {
            throw new IllegalArgumentException("Laboratorio Obrigatorio");
        } else if (Objects.isNull(obj.getInfosPropriedade())) {
            throw new IllegalArgumentException("Propriedade Obrigatorio");
        }

        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Requisicao update(Long id, Requisicao obj) {
        try {
            Requisicao entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Requisicao entity, Requisicao obj) {
//        entity.setId(obj.getId());
        entity.setNome(obj.getNome());
        entity.setDataInicial(obj.getDataInicial());
        entity.setDataFinal(obj.getDataFinal());
        entity.setCnpj(obj.getCnpj());
        entity.setInfosPropriedade(obj.getInfosPropriedade());
        entity.setLaboratorio(obj.getLaboratorio());
        entity.setObservacoes(obj.getObservacoes());
    }
}
