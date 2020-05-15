package com.newidea.cursomc.resources;

import com.newidea.cursomc.domain.Categoria;
import com.newidea.cursomc.domain.Pessoa;
import com.newidea.cursomc.services.CategoriaService;
import com.newidea.cursomc.services.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @Autowired
    private TesteService teste;


    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){

        Categoria obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(Categoria obj){
        obj= service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id]").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
