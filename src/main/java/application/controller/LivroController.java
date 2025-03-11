package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import application.model.Livro;
import application.record.LivroDTO;
import application.repository.LivroRepository;
import application.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private LivroService livroService;

    @GetMapping
    public Iterable<LivroDTO> list() {
        return this.livroService.list();
    }

    @PostMapping
    public LivroDTO insert(@RequestBody LivroDTO livro) {
        return this.livroService.insert(livro);
    }

    @GetMapping("/{id}")
    public LivroDTO getOne(@PathVariable long id) {
        return this.livroService.getOne(id);
    }

    @PutMapping("/{id}")
    public LivroDTO update(@PathVariable long id, @RequestBody LivroDTO livro) {
        return this.livroService.update(id, livro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.livroService.delete(id);
    }
}
