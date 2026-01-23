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
    public PessoaDTO criarPessoa(@RequestBody PessoaDTO pessoa) {
        return pessoaService.criarPessoa(pessoa);
    }

    // Mostrar todas as pessoas(READ)
    @GetMapping("/listar")
    public List<PessoaDTO> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    // Mostrar pessoa por id(READ)
    @GetMapping("/listar/{id}")
    public PessoaDTO listarPessoaPorId(@PathVariable Long id) {
        return pessoaService.listarPessoaPorId(id);
    }

    // Alterar dados da pessoa(UPDATE)
    @PutMapping("/alterar/{id}")
    public PessoaDTO alterarPessoaPorId(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada) {
        return pessoaService.alterarPessoaPorId(id, pessoaAtualizada);
    }

    // Deletar pessoa(DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarPessoaPorId(@PathVariable Long id) {
        pessoaService.deletarPessoaPorId(id);
    }
}
