package com.newidea.cursomc.services;

import com.newidea.cursomc.domain.Cidade;
import com.newidea.cursomc.domain.Cliente;
import com.newidea.cursomc.domain.Endereco;
import com.newidea.cursomc.domain.enums.TipoCliente;
import com.newidea.cursomc.dto.ClienteDTO;
import com.newidea.cursomc.dto.ClienteNewDTO;
import com.newidea.cursomc.repositories.ClienteRepository;
import com.newidea.cursomc.repositories.EnderecoRepository;
import com.newidea.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository respository;

    @Autowired
    public EnderecoRepository enderecoRepository;

    public Cliente find (Integer id){

        Optional<Cliente> obj = respository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: "+id+", Tipo: "+Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = respository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return respository.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            respository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é posssivel excluir um cliente que possua pedidos");
        }
    }

    public List<Cliente> findAll(){
        return respository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesperPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesperPage, Sort.Direction.valueOf(direction), orderBy);

        return respository.findAll(pageRequest);
    }

    public Cliente fromDTO(@Valid ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    private void updateData (Cliente newObj, Cliente obj){
            newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
