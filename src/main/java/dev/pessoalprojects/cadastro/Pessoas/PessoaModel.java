package dev.pessoalprojects.cadastro.Pessoas;

import dev.pessoalprojects.cadastro.Tarefas.TarefasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cadastro_de_pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "Idade")
    private int idade;

    @Column
    private String cidade;
    //Um ninja para váris missões.
    @ManyToOne
    @JoinColumn(name = "tarefas_id")//Foreing key
    private TarefasModel tarefas;


}
