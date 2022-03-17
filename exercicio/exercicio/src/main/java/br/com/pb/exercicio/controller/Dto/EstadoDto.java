package br.com.pb.exercicio.controller.Dto;

import br.com.pb.exercicio.modelo.Estado;
import br.com.pb.exercicio.modelo.Regioes;


import java.util.List;
import java.util.stream.Collectors;

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

    public static List<EstadoDto> converter(List<Estado> estados) {
        return estados.stream().map(EstadoDto::new).collect(Collectors.toList());
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
