package com.example.lembrete.Controller;

import com.example.lembrete.DTO.LembreteDTO;
import com.example.lembrete.Entity.LembreteEntity;
import com.example.lembrete.Repository.LembreteRepository;
import com.example.lembrete.Service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/lembrete")
public class LembreteController {
    @Autowired
    private LembreteService service;
    @Autowired
    private LembreteRepository repository;

    /*@GetMapping
    public ResponseEntity<List<LembreteDTO>> search(@RequestParam("nome") String pessoas) {
        try {
            return ResponseEntity.ok(service.getLembretesPessoa(pessoas));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }*/

    @GetMapping
    public ResponseEntity<List<LembreteEntity>> search(@RequestParam("nome") String pessoas) {
        try {
            return ResponseEntity.ok(service.getLembretesPessoa(pessoas));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /*@PostMapping
    public ResponseEntity<LembreteDTO> create(@RequestBody @Validated LembreteDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }*/

    @PostMapping
    public ResponseEntity<LembreteEntity> create(@RequestBody @Validated LembreteEntity entity) {
        try {
            return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<LembreteDTO> update(@PathVariable("id") Long id, @RequestBody @Validated LembreteDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro " + e.getMessage());
        }
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<LembreteEntity> update(@PathVariable("id") Long id, @RequestBody @Validated LembreteEntity entity) {
        try {
            return new ResponseEntity<>(service.update(id, entity), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            LembreteEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
            repository.delete(entity);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
