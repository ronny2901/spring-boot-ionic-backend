package com.newidea.cursomc.services;

import com.newidea.cursomc.domain.Categoria;
import com.newidea.cursomc.domain.Pedido;
import com.newidea.cursomc.domain.Produto;
import com.newidea.cursomc.repositories.CategoriaRepository;
import com.newidea.cursomc.repositories.PedidoRepository;
import com.newidea.cursomc.repositories.ProdutoRepository;
import com.newidea.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    public ProdutoRepository respository;

    @Autowired
    public CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> obj = respository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesperPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesperPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return respository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
