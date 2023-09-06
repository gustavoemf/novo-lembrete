package com.example.lembrete.Repository;

import com.example.lembrete.Entity.LembreteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LembreteRepository extends JpaRepository<LembreteEntity, Long> {
    List<LembreteEntity> findByPessoas_Nome(String pessoas);
}