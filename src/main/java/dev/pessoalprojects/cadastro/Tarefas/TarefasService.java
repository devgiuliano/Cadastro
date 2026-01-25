package dev.pessoalprojects.cadastro.Tarefas;

import dev.pessoalprojects.cadastro.Pessoas.PessoaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefasService {
    private TarefasRepository tarefasRepository;
    private TarefasMapper tarefasMapper;

    public TarefasService(TarefasRepository tarefasRepository, TarefasMapper tarefasMapper) {
        this.tarefasRepository = tarefasRepository;
        this.tarefasMapper = tarefasMapper;
    }

    // Listar todas as tarefas
    public List<TarefasDTO> mostrarTarefa() {
        List<TarefasModel> tarefa = tarefasRepository.findAll();
        return tarefa.stream()
                .map(tarefasMapper::map)
                .collect((Collectors.toList()));
    }

    // Listtar tarefa por id
    public TarefasDTO listarTarefaPorId(Long id) {
        Optional<TarefasModel> tarefaPorId = tarefasRepository.findById(id);
        return tarefaPorId.map(tarefasMapper::map).orElse(null);
    }

    // Deletar tarefa por id
    public void deletarPorId(Long id) {
        tarefasRepository.deleteById(id);
    }

    /// Criar tarefa
    public TarefasDTO criarTarefa(TarefasDTO tarefaDTO) {
        TarefasModel tarefa = tarefasMapper.map(tarefaDTO);
        tarefa = tarefasRepository.save(tarefa);
        return tarefasMapper.map(tarefa);
    }

    /// Alterar por id
    public TarefasDTO alterarTarefaPorId(Long id, TarefasDTO tarefaDTO) {
       Optional<TarefasModel>  tarefaExistente = tarefasRepository.findById(id);
        if (tarefaExistente.isPresent()) {
            TarefasModel tarefaAtualizada = tarefasMapper.map(tarefaDTO);
            tarefaAtualizada.setId(id);
            TarefasModel tarefaSalva = tarefasRepository.save(tarefaAtualizada);
            return tarefasMapper.map(tarefaSalva);
        }
        return null;
    }
}
