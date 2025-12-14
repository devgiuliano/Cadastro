package dev.pessoalprojects.cadastro.Pessoas;

import dev.pessoalprojects.cadastro.Tarefas.TarefasModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cadastro_de_pessoa")
public class PessoaModel {

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nome;
    private String email;
    private int idade;
    @ManyToOne
    @JoinColumn(name = "Missoes_id")
    private TarefasModel tarefas;

    public PessoaModel() {
    }

    public PessoaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

}
