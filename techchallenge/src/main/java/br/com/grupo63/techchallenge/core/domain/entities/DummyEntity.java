package br.com.grupo63.techchallenge.core.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "_dummy")
public class DummyEntity {
    @Id
    private Long id;

    private String aaaa;
}
