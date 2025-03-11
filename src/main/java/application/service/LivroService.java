package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import application.model.Livro;
import application.record.LivroDTO;
import application.repository.LivroRepository;

@Service
public class LivroService {
   
    @Autowired
    private LivroRepository livroRepo;

    public Iterable<LivroDTO> list() {
        return livroRepo.findAll().stream().map(LivroDTO::new).toList();
    }

    public LivroDTO insert(@RequestBody LivroDTO livro) {
        Livro newLivro = new Livro(livro);
        Livro savedLivro = livroRepo.save(newLivro);
        LivroDTO response = new LivroDTO(savedLivro);
        return response;

    }

    @GetMapping("/{id}")
    public LivroDTO getOne(@PathVariable long id) {
        Optional<Livro> resultado = livroRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado"
            );
        }
        return new LivroDTO(resultado.get());
    }

    @PutMapping("/{id}")
    public LivroDTO update(@PathVariable long id, @RequestBody LivroDTO livro) {
        Optional<Livro> resultado = livroRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado"
            );
        }
        Livro livroExistente = resultado.get();
        livroExistente.setTitulo(livro.titulo());
        livroExistente.setGeneros(livro.generos());
        livroExistente.setAutores(livro.autores());

        return new LivroDTO(livroRepo.save(livroExistente));
    }

    
    public void delete(@PathVariable long id) {
        if(!livroRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não encontrado"
            );
        
        }
        livroRepo.deleteById(id);
    }



    
}
