package br.com.screenmatch.springwebthymealeaf.controller;

import br.com.screenmatch.springwebthymealeaf.domain.filme.DadosAlteracaoFilme;
import br.com.screenmatch.springwebthymealeaf.domain.filme.DadosCadastroFilme;
import br.com.screenmatch.springwebthymealeaf.domain.filme.Filme;
import br.com.screenmatch.springwebthymealeaf.domain.filme.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeRepository filmeRepository;


    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        if (id != null) {
            var filme = filmeRepository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }

    @PostMapping
    @Transactional
    public String cadastrarFilme(DadosCadastroFilme dados) {
        var filme = new Filme(dados);
        filmeRepository.save(filme);

        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String alteraFilme(DadosAlteracaoFilme dados) {
        var filme = filmeRepository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", filmeRepository.findAll());
        return "filmes/listagem";
    }

    @DeleteMapping
    @Transactional
    public String removeFilme(Long id) {
        filmeRepository.deleteById(id);
        return "redirect:/filmes";
    }
}
