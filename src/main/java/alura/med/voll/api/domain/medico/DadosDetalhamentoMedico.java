package alura.med.voll.api.domain.medico;

import alura.med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
                                      Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico(MedicoEntity medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
                medico.getEspecialidade(), medico.getEndereco());
    }
}
