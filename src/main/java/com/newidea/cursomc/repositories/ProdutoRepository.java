package com.newidea.cursomc.repositories;

import com.newidea.cursomc.domain.Categoria;
import com.newidea.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


}
