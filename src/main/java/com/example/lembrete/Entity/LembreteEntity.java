package com.example.lembrete.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lembrete", schema = "public")
public class LembreteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;
    @Column
    private String nome;
    @Column
    private LocalDateTime data;
    @ManyToMany
    @JoinTable(
            name = "lembretes_pessoas",
            joinColumns = @JoinColumn(name = "lembrete_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    private List<PessoaEntity> pessoas = new ArrayList<>();
}
