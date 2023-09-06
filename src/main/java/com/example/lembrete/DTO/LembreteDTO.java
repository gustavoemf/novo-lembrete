package com.example.lembrete.DTO;

import com.example.lembrete.Entity.PessoaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class LembreteDTO {
    private Long id;
    private String nome;
    private LocalDateTime data;
    private List<PessoaEntity> pessoas = new ArrayList<>();
}
