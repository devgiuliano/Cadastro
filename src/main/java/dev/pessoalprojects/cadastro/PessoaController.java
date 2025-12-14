package dev.pessoalprojects.cadastro;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PessoaController {

    @GetMapping("boasvindas")
    public String boasVindas(){
        return "Livia liviazinha is my love ... S2";
    }
}
