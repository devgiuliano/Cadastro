package dev.pessoalprojects.cadastro.Pessoas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // Adicionar pessoa(CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarPessoa(@RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Pessoa criada com sucesso: " + pessoaCriada.getNome());
    }

    // Mostrar todas as pessoas(READ)
    @GetMapping("/listar")
    public List<PessoaDTO> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    // Mostrar pessoa por id(READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPessoaPorId(@PathVariable Long id) {
        PessoaDTO pessoaBuscada = pessoaService.listarPessoaPorId(id);
        if (pessoaBuscada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID não encontrado");
        }
        return ResponseEntity.ok(pessoaBuscada);
    }
    // Alterar dados da pessoa(UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarPessoaPorId(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada) {
        PessoaDTO pessoaAlterada = pessoaService.alterarPessoaPorId(id, pessoaAtualizada);
        if (pessoaAlterada != null){
           return ResponseEntity.ok(pessoaAlterada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("ID não encontrado");
        }
    }

    // Deletar pessoa(DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPessoaPorId(@PathVariable Long id) {
        PessoaDTO pessoaDeletada = pessoaService.listarPessoaPorId(id);
        if (pessoaDeletada != null) {
            pessoaService.deletarPessoaPorId(id);
            return ResponseEntity.ok("Usuario(a): "+pessoaDeletada.getNome() + "deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id não encontrado para deleção ");
        }
    }
}
