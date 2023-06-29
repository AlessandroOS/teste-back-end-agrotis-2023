package com.alessandro.testeagrotis.controller;

import com.alessandro.testeagrotis.domain.Requisicao;
import com.alessandro.testeagrotis.dto.RequisicaoDTO;
import com.alessandro.testeagrotis.service.RequisicaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/requisicoes")
@Api(tags = "Controller de requisicoes")
public class RequisicaoController {
    @Autowired
    private RequisicaoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation("Busca todas as requisicoes")
    public ResponseEntity<List<RequisicaoDTO>> findAll() {
        List<Requisicao> list = service.findAll();
        List<RequisicaoDTO> dtoList = list.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Busca as requisicoes por id")
    public ResponseEntity<RequisicaoDTO> findById(@PathVariable Long id) {
        Requisicao obj = service.findById(id);
        RequisicaoDTO dto = convertEntityToDTO(obj);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation("Insere uma nova requisicao")
    public ResponseEntity<Requisicao> insert(@RequestBody RequisicaoDTO dto) {
        Requisicao requisicao = convertDTOToEntity(dto);
        Requisicao savedRequisicao = service.insert(requisicao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRequisicao.getId()).toUri();
        return ResponseEntity.created(uri).body(savedRequisicao);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deleta uma requisicao por id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualiza uma requisicao por id")
    public ResponseEntity<Requisicao> update(@PathVariable Long id, @RequestBody RequisicaoDTO dto) {
        Requisicao requisicao = convertDTOToEntity(dto);
        Requisicao updatedRequisicao = service.update(id, requisicao);
        return ResponseEntity.ok(updatedRequisicao);
    }

    private RequisicaoDTO convertEntityToDTO(Requisicao requisicao) {
        return modelMapper.map(requisicao, RequisicaoDTO.class);
    }

    private Requisicao convertDTOToEntity(RequisicaoDTO dto) {
        return modelMapper.map(dto, Requisicao.class);
    }
}
