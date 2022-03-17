package br.com.pb.exercicio.repository;

import br.com.pb.exercicio.modelo.Estado;
import br.com.pb.exercicio.modelo.Regioes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Page<Estado> findByRegiao(Regioes regiao, Pageable paginacao);
}
