package dev.pessoalprojects.cadastro.Pessoas;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {
    public PessoaModel map(PessoaDTO pessoaDTO){
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(pessoaDTO.getId());
        pessoaModel.setNome(pessoaDTO.getNome());
        pessoaModel.setCidade(pessoaDTO.getCidade());
        pessoaModel.setIdade(pessoaDTO.getIdade());
        pessoaModel.setEmail(pessoaDTO.getEmail());
        pessoaModel.setTarefas(pessoaDTO.getTarefas());

        return pessoaModel;
    }

    public PessoaDTO map(PessoaModel pessoaModel){
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoaModel.getId());
        pessoaDTO.setNome(pessoaModel.getNome());
        pessoaDTO.setCidade(pessoaModel.getCidade());
        pessoaDTO.setIdade(pessoaModel.getIdade());
        pessoaDTO.setEmail(pessoaModel.getEmail());
        pessoaDTO.setTarefas(pessoaModel.getTarefas());

        return pessoaDTO;
    }
}
