package com.newidea.cursomc.services;

import com.newidea.cursomc.domain.Pessoa;
import com.newidea.cursomc.repositories.PessoaRepository;
import com.newidea.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TesteService {
    @Autowired
    public PessoaRepository respository;

    public Pessoa find (Integer id){

        Optional<Pessoa> obj = respository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: "+id+", Tipo: "+Pessoa.class.getName()));
    }
}
