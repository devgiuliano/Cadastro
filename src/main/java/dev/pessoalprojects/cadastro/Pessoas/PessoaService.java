package dev.pessoalprojects.cadastro.Pessoas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;
    private PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    //Listar todas as pessoas
    public List<PessoaDTO> listarPessoas() {
        List<PessoaModel> pessoa = pessoaRepository.findAll();
        return pessoa.stream()
                .map(pessoaMapper::map)
                .collect((Collectors.toList()));
    }

    //Listar pessoas por id
    public PessoaDTO listarPessoaPorId(Long id) {
        Optional<PessoaModel> pessoaPorId = pessoaRepository.findById(id);
        return pessoaPorId.map(pessoaMapper::map).orElse(null);
    }

    //Criar pessoa
    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) {
        PessoaModel pessoa = pessoaMapper.map(pessoaDTO);
        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.map(pessoa);
    }

    //Deletar pessoa por id(precisa ser um metodo void)
    public void deletarPessoaPorId(Long id) {
        pessoaRepository.deleteById(id);
    }

    //Atualizar pessoa por id
    public PessoaDTO alterarPessoaPorId(Long id, PessoaDTO pessoaDTO) {
        Optional<PessoaModel> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            PessoaModel pessoaAtualizada = pessoaMapper.map(pessoaDTO);
            pessoaAtualizada.setId(id);
            PessoaModel pessoaSalva = pessoaRepository.save(pessoaAtualizada);
            return pessoaMapper.map(pessoaSalva);
        }
        return null;
    }
}

