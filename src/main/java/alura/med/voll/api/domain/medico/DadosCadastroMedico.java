package alura.med.voll.api.domain.medico;

import alura.med.voll.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
        String crm,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        @NotNull(message = "{especialidade.obrigatoria}")
        Especialidade especialidade,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        DadosEndereco endereco) {
}
