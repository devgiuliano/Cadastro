package dev.pessoalprojects.cadastro.Tarefas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefasController {
    private TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @PostMapping("/criar")
    public TarefasDTO criarTarefa(@RequestBody TarefasDTO tarefa) {
        return tarefasService.criarTarefa(tarefa);
    }

    @GetMapping("/listar")
    public List<TarefasDTO> mostrarTarefa() {
        return tarefasService.mostrarTarefa();
    }

    @GetMapping("/listar/{id}")
    public TarefasDTO listarTarefaPorId(@PathVariable Long id) {
        return tarefasService.listarTarefaPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public TarefasDTO alterarTarefaPorId(@PathVariable Long id, @RequestBody TarefasDTO tarefaAtualizada) {
        return tarefasService.alterarTarefaPorId(id, tarefaAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPorId(@PathVariable Long id) {
        tarefasService.deletarPorId(id);
    }


}
