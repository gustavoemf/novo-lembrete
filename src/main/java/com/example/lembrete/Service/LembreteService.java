package com.example.lembrete.Service;

import com.example.lembrete.Config.Mapper;
import com.example.lembrete.DTO.LembreteDTO;
import com.example.lembrete.Entity.LembreteEntity;
import com.example.lembrete.Entity.PessoaEntity;
import com.example.lembrete.Repository.LembreteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LembreteService {
    @Autowired
    private LembreteRepository repository;
    @Autowired
    private Mapper modelMapper;

    public List<LembreteEntity> getLembretesPessoa(String pessoas) {
        return repository.findByPessoas_Nome(pessoas);
    }

    /*@Transactional
    public LembreteDTO create(@RequestBody LembreteDTO dto) {
        return modelMapper.modelMapper().map(repository.save(modelMapper.modelMapper().map(dto, LembreteEntity.class)), LembreteDTO.class);
    }*/

    /*@Transactional
    public LembreteDTO update(Long id, LembreteDTO dto) {
        LembreteEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.modelMapper().map(repository.save(modelMapper.modelMapper().map(dto, LembreteEntity.class)), LembreteDTO.class);
    }*/

    @Transactional
    public LembreteEntity create(@RequestBody LembreteEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public LembreteEntity update(Long id, LembreteEntity entity) {
        LembreteEntity entityBanco = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return repository.save(entityBanco);
    }
}
