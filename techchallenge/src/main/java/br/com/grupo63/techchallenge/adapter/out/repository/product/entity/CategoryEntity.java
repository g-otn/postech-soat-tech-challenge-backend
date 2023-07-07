package br.com.grupo63.techchallenge.adapter.out.repository.product.entity;

import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.core.domain.model.product.Category;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cat_category", indexes = {})
public class CategoryEntity extends DomainEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    public CategoryEntity(Category category) {
        super(category);
        this.name = category.getName();
    }

    public Category toModel() {
        return new Category(this.getId(),
                this.isDeleted(),
                this.getName());
    }
}
