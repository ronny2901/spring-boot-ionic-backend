package com.newidea.cursomc.repositories;

import com.newidea.cursomc.domain.Cliente;
import com.newidea.cursomc.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
