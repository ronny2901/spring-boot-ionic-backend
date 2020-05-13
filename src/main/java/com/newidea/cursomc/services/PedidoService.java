package com.newidea.cursomc.services;

import com.newidea.cursomc.domain.Categoria;
import com.newidea.cursomc.domain.Pedido;
import com.newidea.cursomc.repositories.PedidoRepository;
import com.newidea.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    public PedidoRepository respository;

    public Pedido find(Integer id) {

        Optional<Pedido> obj = respository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
