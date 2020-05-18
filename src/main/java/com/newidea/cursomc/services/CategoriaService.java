package com.newidea.cursomc.services;

import com.newidea.cursomc.domain.Categoria;
import com.newidea.cursomc.dto.CategoriaDTO;
import com.newidea.cursomc.repositories.CategoriaRepository;
import com.newidea.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository respository;

    public Categoria find (Integer id){

        Optional<Categoria> obj = respository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: "+id+", Tipo: "+Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){

        obj.setId(null);

        return respository.save(obj);
    }

    public Categoria update(Categoria obj){
        find(obj.getId());
        return respository.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try{
            respository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é posssivel excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll(){
        return respository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesperPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesperPage, Sort.Direction.valueOf(direction), orderBy);

        return respository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO obj){
        return new Categoria(obj.getId(), obj.getNome());
    }

}
