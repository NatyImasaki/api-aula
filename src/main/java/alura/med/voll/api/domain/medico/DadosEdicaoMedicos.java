package alura.med.voll.api.domain.medico;

import alura.med.voll.api.domain.endereco.DadosEndereco;

public record DadosEdicaoMedicos(Long id, String nome, String telefone, DadosEndereco endereco) {
}
