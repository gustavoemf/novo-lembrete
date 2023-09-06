package com.example.lembrete.Controller;

import com.example.lembrete.DTO.PessoaDTO;
import com.example.lembrete.Entity.PessoaEntity;
import com.example.lembrete.Repository.PessoaRepository;
import com.example.lembrete.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;
    @Autowired
    private PessoaRepository repository;

    /*@PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Validated PessoaDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }*/

    @PostMapping
    public ResponseEntity<PessoaEntity> create(@RequestBody @Validated PessoaEntity entity) {
        try {
            return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable("id") final Long id, @RequestBody @Validated PessoaDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro " + e.getMessage());
        }
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<PessoaEntity> update(@PathVariable("id") final Long id, @RequestBody @Validated PessoaEntity entity) {
        try {
            return new ResponseEntity<>(service.update(id, entity), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            PessoaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
            repository.delete(entity);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
