package com.alessandro.testeagrotis.service;

import com.alessandro.testeagrotis.domain.Laboratorio;
import com.alessandro.testeagrotis.repository.LaboratorioRepository;
import com.alessandro.testeagrotis.service.exceptions.DatabaseException;
import com.alessandro.testeagrotis.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public List<Laboratorio> findAll() {
        return laboratorioRepository.findAll();
    }

    public Laboratorio findById(Long id) {
        Optional<Laboratorio> obj = laboratorioRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado com o ID: " + id));
    }

    public Laboratorio insert(Laboratorio obj) {
        return laboratorioRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            laboratorioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Laboratório não encontrado com o ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade ao excluir o laboratório com o ID: " + id);
        }
    }

    public Laboratorio update(Long id, Laboratorio obj) {
        try {
            Laboratorio entity = laboratorioRepository.getById(id);
            updateData(entity, obj);
            return laboratorioRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Laboratório não encontrado com o ID: " + id);
        }
    }

    private void updateData(Laboratorio entity, Laboratorio obj) {
        entity.setNome(obj.getNome());
    }
}


