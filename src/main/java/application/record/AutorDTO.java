package application.record;

import application.model.Autor;

public record AutorDTO(long id, String nome) {
    public AutorDTO(Autor entidade) {
        this(entidade.getId(), entidade.getNome());
    }
    
}
