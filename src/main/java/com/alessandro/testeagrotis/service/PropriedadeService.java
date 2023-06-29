package com.alessandro.testeagrotis.service;

import com.alessandro.testeagrotis.domain.Propriedade;
import com.alessandro.testeagrotis.repository.PropriedadeRepository;
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
public class PropriedadeService {
    @Autowired
    private PropriedadeRepository propriedadeRepository;

    public List<Propriedade> findAll() {
        return propriedadeRepository.findAll();
    }

    public Propriedade findById(Long id) {
        Optional<Propriedade> obj = propriedadeRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Propriedade não encontrada com o ID: " + id));
    }

    public Propriedade insert(Propriedade obj) {
        return propriedadeRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            propriedadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Propriedade não encontrada com o ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade ao excluir a propriedade com o ID: " + id);
        }
    }

    public Propriedade update(Long id, Propriedade obj) {
        try {
            Propriedade entity = propriedadeRepository.getById(id);
            updateData(entity, obj);
            return propriedadeRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Propriedade não encontrada com o ID: " + id);
        }
    }

    private void updateData(Propriedade entity, Propriedade obj) {
        entity.setNome(obj.getNome());
    }
}


