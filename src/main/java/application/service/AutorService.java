package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.Autor;
import application.record.AutorDTO;
import application.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepo;

    public AutorDTO insert(AutorDTO dados) {
        return new AutorDTO(autorRepo.save(new Autor(dados)));
    }

    public Iterable<AutorDTO> findAll() {
        return autorRepo.findAll().stream().map(AutorDTO::new).toList();
    }
    
}
