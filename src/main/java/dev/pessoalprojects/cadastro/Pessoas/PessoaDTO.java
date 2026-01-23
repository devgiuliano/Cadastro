package dev.pessoalprojects.cadastro.Pessoas;
import dev.pessoalprojects.cadastro.Tarefas.TarefasModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private String email;
    private int idade;
    private String cidade;
    private TarefasModel tarefas;
}
