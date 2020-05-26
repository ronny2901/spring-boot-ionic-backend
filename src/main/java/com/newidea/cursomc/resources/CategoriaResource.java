package com.newidea.cursomc.resources;

import com.newidea.cursomc.domain.Categoria;
import com.newidea.cursomc.domain.Pessoa;
import com.newidea.cursomc.dto.CategoriaDTO;
import com.newidea.cursomc.services.CategoriaService;
import com.newidea.cursomc.services.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @Autowired
    private TesteService teste;


    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){

        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){
        Categoria obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id]").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
        Categoria obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.insert(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){

         service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){

        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value="/page", method= RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value="page", defaultValue = "0") Integer page,
                                                       @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesperPage,
                                                       @RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(value="direction", defaultValue = "ASC") String direction){

        Page<Categoria> list = service.findPage(page, linesperPage, orderBy, direction);
        Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }


}
