package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Livro;
import application.record.LivroDTO;
import application.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepo;

    @GetMapping
    public Iterable<LivroDTO> list() {
        return livroRepo.findAll().stream().map(LivroDTO::new).toList();
    }

    @GetMapping("/{id}")
    public LivroDTO getOne(@PathVariable long id) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não Encontrado"
            );
        }
        return new LivroDTO(resultado.get());
    }

    @PostMapping
    public LivroDTO insert(@RequestBody LivroDTO livro) {
        Livro newLivro = new Livro(livro);
        Livro savedLivro = livroRepo.save(newLivro);
        LivroDTO response = new LivroDTO(savedLivro);
        return response;
    }

    @PutMapping("/{id}")
    public LivroDTO update(@PathVariable long id, @RequestBody LivroDTO livro) {
        Optional<Livro> resultado = livroRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não Encontrado"
            );
        }

        resultado.get().setTitulo(livro.titulo());
        resultado.get().setGeneros(livro.generos());
        resultado.get().setAutores(livro.autores());

        return new LivroDTO(livroRepo.save(resultado.get()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!livroRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não Encontrado"
            );
        }
        livroRepo.deleteById(id);
    }
}
