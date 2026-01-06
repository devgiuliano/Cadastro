package dev.pessoalprojects.cadastro.Pessoas;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // Adicionar pessoa(CREATE)
    @PostMapping("/criar")
    public String criarPessoa() {
        return "Pessoa criada";
    }

    // Mostrar todas as pessoas(READ)
    @GetMapping("/listar")
    public List<PessoaModel>listarPessoas() {
        return pessoaService.listarPessoas();
    }

    // Mostrar pessoa por id(READ)
    @GetMapping("/listar/{id}")
    public PessoaModel listarPessoaPorId(@PathVariable Long id){
        return pessoaService.listarPessoaPorId(id);
    }

    // Alterar dados da pessoa(UPDATE)
    @PutMapping("/alterarID")
    public String alterarPessoaPorId() {
        return "Alterar pessoa por id";
    }

    // Deletar pessoa(DELETE)
    @DeleteMapping("/deletarID")
    public String deletarPessoaPorId() {
        return "Pessoa deletada por id";
    }
}
