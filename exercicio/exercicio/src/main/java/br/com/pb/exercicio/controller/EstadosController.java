package br.com.pb.exercicio.controller;

import br.com.pb.exercicio.controller.Dto.EstadoDto;
import br.com.pb.exercicio.controller.form.AtualizacaoEstadoForm;
import br.com.pb.exercicio.controller.form.EstadoForm;
import br.com.pb.exercicio.modelo.Estado;
import br.com.pb.exercicio.modelo.Regioes;
import br.com.pb.exercicio.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



import javax.transaction.Transactional;
import javax.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping//GET
    public List<EstadoDto> listar(@RequestParam(required = false) Regioes regiao, @RequestParam(required = false) String ordenacao){

        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0,10, sort);

        if(ordenacao!=null){
            if(ordenacao.equals("populacao")){
                sort = Sort.by("populacao").descending();
                pageable = PageRequest.of(0,10, sort);
            }else if (ordenacao.equals("area")){
                sort = Sort.by("area").descending();
                pageable = PageRequest.of(0,10, sort);
            }
        }
    if(regiao == null){
        Page<Estado> estados = estadoRepository.findAll(pageable);
        return EstadoDto.converter(estados).getContent();
    }else {
        Page<Estado> estados = estadoRepository.findByRegiao(regiao, pageable);
        return EstadoDto.converter(estados).getContent();
        }
    }

    @PostMapping//POST
    @Transactional
    public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder){
        Estado estado = form.converter();
        estadoRepository.save(estado);

        URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstadoDto(estado));
    }

    @GetMapping("/{id}")//GET
    public ResponseEntity<EstadoDto> detalhar(@PathVariable Long id){

        Optional<Estado> estado = estadoRepository.findById(id);
        if(estado.isPresent()){
            return ResponseEntity.ok(new EstadoDto(estado.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}") //PUT
    @Transactional
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadoForm form ){
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            Estado estado = form.atualizar(id, estadoRepository);
            return ResponseEntity.ok(new EstadoDto(estado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()) {
            estadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


