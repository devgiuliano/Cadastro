package dev.pessoalprojects.cadastro.Tarefas;

import dev.pessoalprojects.cadastro.Pessoas.PessoaModel;
import jakarta.persistence.*;

@Entity
@Table(name = "cadastro_tarefas")
public class TarefasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dificuldade;
    @OneToMany(mappedBy = "missoes")
    private PessoaModel pessoas;
}
