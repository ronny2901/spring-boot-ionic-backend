package com.newidea.cursomc.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categoria  implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;

}
