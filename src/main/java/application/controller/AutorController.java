package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.AutorDTO;
import application.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorService autoService;

    @GetMapping
    public  Iterable<AutorDTO> list() {
        return autoService.findAll();
    }

    @PostMapping
    public AutorDTO insert(@RequestBody AutorDTO autor) {
        return autoService.insert(autor);
    }
          
 }
    

