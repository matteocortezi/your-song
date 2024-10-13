package com.fiap.musicas.controller;

import com.fiap.musicas.model.Musica;
import com.fiap.musicas.repository.MusicaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("musica")
public class MusicaController {

    @Autowired
    private MusicaRepository musicaRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Musica musica, Model model){
        return "musica/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Musica musica, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "musica/cadastrar";
        }
        musicaRepository.save(musica);
        redirectAttributes.addFlashAttribute("mensagem", "A música foi cadastrada com sucesso!");
        return "redirect:/musica/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("musica", musicaRepository.findAll());
        return "musica/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesMusica(@PathVariable Long id, Model model) {
        Optional<Musica> optionalMusica = musicaRepository.findById(id);
        if (optionalMusica.isPresent()) {
            model.addAttribute("musica", optionalMusica.get());
        } else {
            model.addAttribute("erro", "Música não encontrada!");
            return "error";
        }
        return "musica/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarMusicas(@RequestParam String query, Model model) {
        List<Musica> musicas = musicaRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("musicas", musicas);
        return "musica/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("musica", musicaRepository.findById(id));
        return "musica/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Musica musica, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "musica/editar";
        }
        musicaRepository.save(musica);
        redirectAttributes.addFlashAttribute("mensagem", "A música foi atualizada!");
        return "redirect:/musica/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long codigo, RedirectAttributes redirectAttributes) {
        musicaRepository.deleteById(codigo);
        redirectAttributes.addFlashAttribute("mensagem", "musica removida com sucesso");
        return "redirect:/musica/listar";
    }

}
