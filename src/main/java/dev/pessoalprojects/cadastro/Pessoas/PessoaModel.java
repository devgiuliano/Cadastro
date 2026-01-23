package dev.pessoalprojects.cadastro.Pessoas;

import dev.pessoalprojects.cadastro.Tarefas.TarefasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_cadastro_de_pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tarefas")
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

    @Column(name = "Cidade")
    private String cidade;

    //Uma pessoa para váris tarefas.
    @ManyToOne
    @JoinColumn(name = "tarefas_id")//Foreing key
    private TarefasModel tarefas;


}
