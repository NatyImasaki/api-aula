package alura.med.voll.api.domain.Usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsario(
        @NotBlank(message = "Usuário é obrigatório")
        String login,
        @NotBlank(message = "Senha é obrigatória")
        String senha) {
}
