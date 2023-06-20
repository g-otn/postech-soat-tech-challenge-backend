package br.com.grupo63.techchallenge.core.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "fil_file", indexes = {})
public class File extends DomainEntity {
}
