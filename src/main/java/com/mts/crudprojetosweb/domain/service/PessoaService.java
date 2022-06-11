package com.mts.crudprojetosweb.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mts.crudprojetosweb.domain.model.Pessoa;
import com.mts.crudprojetosweb.domain.repository.PessoaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PessoaService {
	private PessoaRepository pessoaRepository;
	
	/*esse metodo deve ser executado dentro de uma transação no banco de dados. caso aja um erro, será descartada.Ou seja, é tudo ou nada*/
	
	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public void remover(long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}
}
