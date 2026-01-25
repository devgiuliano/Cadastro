package dev.pessoalprojects.cadastro.Tarefas;

import dev.pessoalprojects.cadastro.Pessoas.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefasDTO {

    private Long id;
    private String nome;
    private String dificuldade;
    private List<PessoaModel> pessoas;

}
