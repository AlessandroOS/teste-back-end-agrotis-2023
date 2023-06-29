package com.alessandro.testeagrotis.controller;

import com.alessandro.testeagrotis.domain.Laboratorio;
import com.alessandro.testeagrotis.dto.LaboratorioDTO;
import com.alessandro.testeagrotis.service.LaboratorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/laboratorios")
@Api(tags = "Controller de laboratórios")
public class LaboratorioController {
    @Autowired
    private LaboratorioService laboratorioService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation("Busca todos os laboratórios")
    public ResponseEntity<List<LaboratorioDTO>> findAll() {
        List<Laboratorio> list = laboratorioService.findAll();
        List<LaboratorioDTO> dtoList = list.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Busca um laboratório por id")
    public ResponseEntity<LaboratorioDTO> findById(@PathVariable Long id) {
        Laboratorio obj = laboratorioService.findById(id);
        LaboratorioDTO dto = convertEntityToDTO(obj);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation("Insere um novo laboratório")
    public ResponseEntity<LaboratorioDTO> insert(@RequestBody LaboratorioDTO dto) {
        Laboratorio laboratorio = convertDTOToEntity(dto);
        Laboratorio savedLaboratorio = laboratorioService.insert(laboratorio);
        LaboratorioDTO savedDto = convertEntityToDTO(savedLaboratorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deleta um laboratório por id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laboratorioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualiza um laboratório por id")
    public ResponseEntity<LaboratorioDTO> update(@PathVariable Long id, @RequestBody LaboratorioDTO dto) {
        Laboratorio laboratorio = convertDTOToEntity(dto);
        Laboratorio updatedLaboratorio = laboratorioService.update(id, laboratorio);
        LaboratorioDTO updatedDto = convertEntityToDTO(updatedLaboratorio);
        return ResponseEntity.ok(updatedDto);
    }

    private LaboratorioDTO convertEntityToDTO(Laboratorio laboratorio) {
        return modelMapper.map(laboratorio, LaboratorioDTO.class);
    }

    private Laboratorio convertDTOToEntity(LaboratorioDTO dto) {
        return modelMapper.map(dto, Laboratorio.class);
    }
}

