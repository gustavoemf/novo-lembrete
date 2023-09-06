package com.example.lembrete.Repository;

import com.example.lembrete.Entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {}
