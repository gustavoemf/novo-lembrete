package com.example.lembrete.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "pessoa", schema = "public")
public class PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;
    @Column
    private String nome;
    @ManyToMany(mappedBy = "pessoas")
    private List<LembreteEntity> lembretes;

    public PessoaEntity(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
