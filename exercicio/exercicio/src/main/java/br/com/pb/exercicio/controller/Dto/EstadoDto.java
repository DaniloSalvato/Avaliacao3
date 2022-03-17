package br.com.pb.exercicio.controller.Dto;

import br.com.pb.exercicio.modelo.Estado;
import br.com.pb.exercicio.modelo.Regioes;
import org.springframework.data.domain.Page;

public class EstadoDto {

    private Long id;
    private String nome;
    private Regioes regiao;
    private Long populacao;
    private String capital;
    private Long area;

    public EstadoDto(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.regiao = estado.getRegiao();
        this.populacao = estado.getPopulacao();
        this.capital = estado.getCapital();
        this.area = estado.getArea();
    }

    public static Page<EstadoDto> converter(Page<Estado> estados) {
        return estados.map(EstadoDto::new);
    }

        public Long getId () {
            return id;
        }

        public String getNome () {
            return nome;
        }

        public Regioes getRegiao () {
            return regiao;
        }

        public Long getPopulacao () {
            return populacao;
        }

        public String getCapital () {
            return capital;
        }

        public Long getArea () {
            return area;
        }

}
