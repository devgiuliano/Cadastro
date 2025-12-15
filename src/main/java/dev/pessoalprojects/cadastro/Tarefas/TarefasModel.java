package dev.pessoalprojects.cadastro.Tarefas;


import dev.pessoalprojects.cadastro.Pessoas.PessoaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_cadastro_tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dificuldade;
    // Uma missão pode ter vários ninjas.
    @OneToMany(mappedBy = "tarefas")
    private List<PessoaModel> pessoas;
}
