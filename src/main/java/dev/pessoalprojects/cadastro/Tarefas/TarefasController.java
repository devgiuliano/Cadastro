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
    public String criarTarefa() {
        return "Tarefa criada com sucesso";
    }

    @GetMapping("/listar")
    public List<TarefasModel> mostrarTarefa(){
        return tarefasService.mostrarTarefa();
    }

    @GetMapping("/listar/{id}")
    public TarefasModel listarTarefaPorId(@PathVariable Long id) {
        return tarefasService.listarTarefaPorId(id);
    }

    @PutMapping("/alterarID")
    public String alterarTarefaPorId() {
        return "Tarefa Alterada";
    }

    @DeleteMapping("/deletarID")
    public String deletarPorId() {
        return "Deletar por id";
    }
}
