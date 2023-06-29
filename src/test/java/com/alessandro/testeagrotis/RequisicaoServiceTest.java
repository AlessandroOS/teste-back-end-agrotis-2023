package com.alessandro.testeagrotis;

import com.alessandro.testeagrotis.domain.Laboratorio;
import com.alessandro.testeagrotis.domain.Propriedade;
import com.alessandro.testeagrotis.domain.Requisicao;
import com.alessandro.testeagrotis.repository.RequisicaoRepository;
import com.alessandro.testeagrotis.service.RequisicaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RequisicaoServiceTest {

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @InjectMocks
    private RequisicaoService requisicaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Requisicao> requisicoes = new ArrayList<>();
        requisicoes.add(new Requisicao());
        when(requisicaoRepository.findAll()).thenReturn(requisicoes);
        List<Requisicao> result = requisicaoService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Requisicao requisicao = new Requisicao();
        requisicao.setId(id);
        when(requisicaoRepository.findById(id)).thenReturn(Optional.of(requisicao));
        Requisicao result = requisicaoService.findById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testInsert() {
        Requisicao requisicao = new Requisicao();
        requisicao.setDataInicial(Instant.now());
        requisicao.setDataFinal(Instant.now());
        requisicao.setLaboratorio(new Laboratorio());
        requisicao.setInfosPropriedade(new Propriedade());
        when(requisicaoRepository.save(requisicao)).thenReturn(requisicao);
        Requisicao result = requisicaoService.insert(requisicao);
        assertNotNull(result);
    }


    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(requisicaoRepository).deleteById(id);
        assertDoesNotThrow(() -> requisicaoService.delete(id));
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Requisicao requisicao = new Requisicao();
        requisicao.setId(id);
        when(requisicaoRepository.getById(id)).thenReturn(requisicao);
        when(requisicaoRepository.save(requisicao)).thenReturn(requisicao);
        Requisicao result = requisicaoService.update(id, requisicao);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}
