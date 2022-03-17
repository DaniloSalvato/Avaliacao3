package br.com.pb.exercicio.repository;

import br.com.pb.exercicio.modelo.Estado;
import br.com.pb.exercicio.modelo.Regioes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    List<Estado> findByRegiao(Regioes regiao);
}
