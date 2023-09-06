package com.example.lembrete;

import com.example.lembrete.Entity.PessoaEntity;
import com.example.lembrete.Repository.PessoaRepository;
import com.example.lembrete.Service.PessoaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class LembreteApplicationTests {
	@MockBean
	PessoaRepository pessoaRepository;

	private final PessoaService service = new PessoaService();

	@BeforeEach
	void setUp() {
		PessoaEntity pessoa = new PessoaEntity(1L, "Gustavo");
		Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
		Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
	}

	@Test
	void testGetIdPessoa() {
		var test = pessoaRepository.findById(1L);
		Long id = test.get().getId();

		System.out.println(id);

		Assert.assertEquals(1L, id,0);
	}

}
