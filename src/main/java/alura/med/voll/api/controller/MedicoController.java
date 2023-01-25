package alura.med.voll.api.controller;

import alura.med.voll.api.domain.medico.*;
import alura.med.voll.api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin (origins = "*")
@RestController
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping("/cadastro/medicos")
    public ResponseEntity cadastraMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        MedicoEntity medico = new MedicoEntity(dados);
        repository.save(medico);

        URI uri = uriBuilder.path("/cadastro/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping("/listagem/medicos")
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
       Page page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
       return ResponseEntity.ok(page);
    }
    @PutMapping("atualiza/medicos")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosEdicaoMedicos dados){
        MedicoEntity medico = repository.getReferenceById(dados.id());
        medico.atualizaInformacao(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("excluir/medicos/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        MedicoEntity medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("detalhar/medicos/{id}")
    public ResponseEntity delhar(@PathVariable Long id){

        MedicoEntity medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
