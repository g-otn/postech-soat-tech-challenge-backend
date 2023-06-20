package br.com.grupo63.techchallenge.core.domain.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cat_category", indexes = {})
public class Category extends DomainEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

}
