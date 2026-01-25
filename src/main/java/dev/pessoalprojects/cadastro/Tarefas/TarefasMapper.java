package dev.pessoalprojects.cadastro.Tarefas;

import org.springframework.stereotype.Component;

@Component
public class TarefasMapper {
    public TarefasModel map(TarefasDTO tarefasDTO){
        TarefasModel tarefasModel = new TarefasModel();
        tarefasModel.setId(tarefasDTO.getId());
        tarefasModel.setNome(tarefasDTO.getNome());
        tarefasModel.setDificuldade(tarefasDTO.getDificuldade());
        tarefasModel.setPessoas(tarefasDTO.getPessoas());

        return tarefasModel;
    }
    public TarefasDTO map(TarefasModel tarefasModel){
        TarefasDTO tarefasDTO = new TarefasDTO();
        tarefasDTO.setId(tarefasModel.getId());
        tarefasDTO.setNome(tarefasModel.getNome());
        tarefasDTO.setDificuldade(tarefasModel.getDificuldade());
        tarefasDTO.setPessoas(tarefasModel.getPessoas());

        return tarefasDTO;
    }
}
