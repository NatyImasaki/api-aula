package alura.med.voll.api.controller;

import alura.med.voll.api.domain.Usuario.DadosAutenticacao;
import alura.med.voll.api.domain.Usuario.UsuarioEntity;
import alura.med.voll.api.infra.security.TokenService;
import alura.med.voll.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity efetuaLogin( @RequestBody @Valid DadosAutenticacao dados){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.login(),
                dados.senha());
        Authentication authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((UsuarioEntity) authentication.getPrincipal()));

    }
}
