package br.com.grupo63.techchallenge.core.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cat_category", indexes = {})
public class Category extends DomainEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    public Category(Long id) {
        this.id = id;
    }
}
