package com.example.lembrete.Service;

import com.example.lembrete.Config.Mapper;
import com.example.lembrete.DTO.PessoaDTO;
import com.example.lembrete.Entity.PessoaEntity;
import com.example.lembrete.Repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;
    @Autowired
    private Mapper modelMapper;

    /*@Transactional
    public PessoaDTO create(@RequestBody PessoaDTO dto) {
        return modelMapper.modelMapper().map(repository.save(modelMapper.modelMapper().map(dto, PessoaEntity.class)), PessoaDTO.class);
    }*/

    /*@Transactional
    public PessoaDTO update(Long id, PessoaDTO dto) {
        PessoaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.modelMapper().map(repository.save(modelMapper.modelMapper().map(dto, PessoaEntity.class)), PessoaDTO.class);
    }*/

    @Transactional
    public PessoaEntity create(@RequestBody PessoaEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public PessoaEntity update(Long id, PessoaEntity entity) {
        PessoaEntity entityBanco = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return repository.save(entityBanco);
    }
}
