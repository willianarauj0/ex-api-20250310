package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import application.repository.GeneroRepository;
import application.record.GeneroDTO;
import application.model.Genero;

@Service
public class GeneroService {
     @Autowired
    private GeneroRepository generoRepo;

    public Iterable<GeneroDTO> list() {
        return generoRepo.findAll().stream().map(GeneroDTO::new).toList();
    }

    public GeneroDTO insert(@RequestBody GeneroDTO genero) {
        Genero newGenero = new Genero(genero);
        Genero savedGenero = generoRepo.save(newGenero);
        GeneroDTO response = new GeneroDTO(savedGenero);
        return response;
    }

    public GeneroDTO getOne(@PathVariable long id) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Genero Não Encontrado");
        }
        return new GeneroDTO(resultado.get());
    }

    public GeneroDTO update(@PathVariable long id, @RequestBody GeneroDTO genero) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Genero Não Encontrado");
        }
        resultado.get().setDescricao(genero.descricao());

        return new GeneroDTO(generoRepo.save(resultado.get()));
    }

    public void delete(@PathVariable long id) {
        if (!generoRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Genero Não Encontrado");
        }

        generoRepo.deleteById(id);
    }
}