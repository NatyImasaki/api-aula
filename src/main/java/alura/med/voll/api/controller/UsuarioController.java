package alura.med.voll.api.controller;


import alura.med.voll.api.domain.Usuario.DadosCadastroUsario;
import alura.med.voll.api.domain.Usuario.UsuarioEntity;
import alura.med.voll.api.domain.medico.DadosDetalhamentoMedico;
import alura.med.voll.api.domain.medico.MedicoEntity;
import alura.med.voll.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping("/cadastro/usuario")
    public ResponseEntity cadastraUsuario(@RequestBody @Valid DadosCadastroUsario dados, UriComponentsBuilder uriBuilder){
        UsuarioEntity usuario = new UsuarioEntity(dados);
        repository.save(usuario);

        URI uri = uriBuilder.path("/cadastro/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuario);

    }
}
