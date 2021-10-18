package br.com.funchal.cadastro.repositorys;

import br.com.funchal.cadastro.models.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Page<Pessoa> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);
}
