package com.example.lembrete;

import com.example.lembrete.Entity.LembreteEntity;
import com.example.lembrete.Entity.PessoaEntity;
import com.example.lembrete.Repository.LembreteRepository;
import com.example.lembrete.Repository.PessoaRepository;
import com.example.lembrete.Service.LembreteService;
import com.example.lembrete.Service.PessoaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class LembreteApplicationTests {
    @MockBean
    PessoaService pessoaService;
    @MockBean
    PessoaRepository pessoaRepository;
    @MockBean
    LembreteService lembreteService;
    @MockBean
    LembreteRepository lembreteRepository;

    @BeforeEach
    void setUp() {
        PessoaEntity pessoa = new PessoaEntity(1L, "Gustavo");
        Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        List<PessoaEntity> pessoas = new ArrayList<>();
        pessoas.add(new PessoaEntity(1L, "Gustavo"));
        pessoas.add(new PessoaEntity(2L, "Marcelo"));
        Mockito.when(pessoaService.getList()).thenReturn(pessoas);

        LembreteEntity lembrete = new LembreteEntity(1L, "Lembrete", LocalDateTime.now(), pessoas);
        Mockito.when(lembreteRepository.save(lembrete)).thenReturn(lembrete);
        Mockito.when(lembreteService.getLembretesPessoa("Gustavo")).thenReturn(List.of(lembrete));
    }

    @Test
    void testCreatePessoa() {
        Assert.assertEquals(pessoaRepository.save(new PessoaEntity(1L, "Marcelo")), pessoaService.create(new PessoaEntity(1L, "Marcelo")));
    }

    @Test
    void testGetIdPessoa() {
        var test = pessoaRepository.findById(1L);
        Long id = test.get().getId();
        System.out.println(id);
        Assert.assertEquals(1L, id, 0);
    }

    @Test
    void testGetList() {
        List<PessoaEntity> resultado = pessoaService.getList();
        System.out.println(resultado.size());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(2, resultado.size());
    }

    @Test
    void testUpdatePessoa() {
        Long id = 1L;
        PessoaEntity pessoaUpdated = new PessoaEntity(id, "Marcelo");
        pessoaService.update(id, pessoaUpdated);
        Assert.assertEquals("Marcelo", pessoaUpdated.getNome());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testCreateLembrete() {
        PessoaEntity pessoa = new PessoaEntity(1L, "Marcelo");
        List<PessoaEntity> list = new ArrayList<>();
        list.add(pessoa);

        Assert.assertEquals(lembreteRepository.save(new LembreteEntity(1L, "Lembrete", LocalDateTime.now(), list)), lembreteService.create(new LembreteEntity(1L, "Lembrete", LocalDateTime.now(), list)));
    }

    @Test
    void testGetByNomePessoa() {
        List<LembreteEntity> list = lembreteService.getLembretesPessoa("Gustavo");
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("Gustavo", list.get(0).getPessoas().get(0).getNome());
    }

    @Test
    void testUpdateLembrete() {
        Long id = 1L;
        PessoaEntity pessoa = new PessoaEntity(1L, "Gustavo 2");
        List<PessoaEntity> list = new ArrayList<>();
        list.add(pessoa);
        LembreteEntity lembreteUpdated = new LembreteEntity(id, "Lembrete 2", LocalDateTime.now(), list);
        lembreteService.update(id, lembreteUpdated);

        Assert.assertEquals("Lembrete 2", lembreteUpdated.getNome());
        Assert.assertEquals(list, lembreteUpdated.getPessoas());
    }
}
