package com.mts.crudprojetosweb.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mts.crudprojetosweb.domain.exception.NegocioException;
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
		boolean emailEmUso = pessoaRepository.findByEmail(pessoa.getEmail()) //regra para não criar pessoa com email repetido
				.stream() //regra para não criar pessoa com email repetido
				.anyMatch(pessoaExistente -> !pessoaExistente.equals(pessoa)); //regra para não criar pessoa com email repetido
		
		if(emailEmUso) {
			throw new NegocioException("Já existe uma pessoa cadastrada com esse e-mail.");
		}
		
		return pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public void remover(long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}
}
