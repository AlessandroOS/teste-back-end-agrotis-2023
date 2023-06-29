package com.alessandro.testeagrotis.controller;

import com.alessandro.testeagrotis.domain.Propriedade;
import com.alessandro.testeagrotis.dto.PropriedadeDTO;
import com.alessandro.testeagrotis.service.PropriedadeService;
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
@RequestMapping(value = "/propriedades")
@Api(tags = "Controller de propriedades")
public class PropriedadeController {
    @Autowired
    private PropriedadeService propriedadeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation("Busca todas as propriedades")
    public ResponseEntity<List<PropriedadeDTO>> findAll() {
        List<Propriedade> list = propriedadeService.findAll();
        List<PropriedadeDTO> dtoList = list.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Busca uma propriedade por id")
    public ResponseEntity<PropriedadeDTO> findById(@PathVariable Long id) {
        Propriedade obj = propriedadeService.findById(id);
        PropriedadeDTO dto = convertEntityToDTO(obj);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation("Insere uma nova propriedade")
    public ResponseEntity<PropriedadeDTO> insert(@RequestBody PropriedadeDTO dto) {
        Propriedade propriedade = convertDTOToEntity(dto);
        Propriedade savedPropriedade = propriedadeService.insert(propriedade);
        PropriedadeDTO savedDto = convertEntityToDTO(savedPropriedade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deleta uma propriedade por id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propriedadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualiza uma propriedade por id")
    public ResponseEntity<PropriedadeDTO> update(@PathVariable Long id, @RequestBody PropriedadeDTO dto) {
        Propriedade propriedade = convertDTOToEntity(dto);
        Propriedade updatedPropriedade = propriedadeService.update(id, propriedade);
        PropriedadeDTO updatedDto = convertEntityToDTO(updatedPropriedade);
        return ResponseEntity.ok(updatedDto);
    }

    private PropriedadeDTO convertEntityToDTO(Propriedade propriedade) {
        return modelMapper.map(propriedade, PropriedadeDTO.class);
    }

    private Propriedade convertDTOToEntity(PropriedadeDTO dto) {
        return modelMapper.map(dto, Propriedade.class);
    }
}



