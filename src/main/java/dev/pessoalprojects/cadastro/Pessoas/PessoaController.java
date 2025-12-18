package dev.pessoalprojects.cadastro.Pessoas;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PessoaController {

    @GetMapping("boasvindas")
    public String boasVindas(){
        return "Livia liviazinha is my love ... S2";
    }
    // Adicionar pessoa(CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Pessoa criada";
    }
    // Mostrar pessoa por id(READ)
    @GetMapping("/todosID")
    public String mostrarPessoaPorId(){
        return "Mostrar pessoas por id";
    }
    // Mostrar todas as pessoas(READ)
    @GetMapping("/todos")
    public String mostrarTodasAsPessoas(){
        return "Mostrar todas as pessoas";
    }
    // Alterar dados da pessoa(UPDATE)
    @PutMapping("/alterarID")
    public String alterarPessoaPorId(){
        return "Alterar pessoa por id";
    }
    // Deletar pessoa(DELETE)
    @DeleteMapping("/deletarPorID")
    public String deletarPessoaPorId(){
        return "Pessoa deletada por id";
    }
}
