package br.com.grupo63.techchallenge.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PACKAGE)

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class DomainEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @jakarta.persistence.Id
    @Access(AccessType.FIELD)
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Basic
    @Column(name = "deleted", nullable = false)
    protected boolean deleted = false;

    @Column(name = "creation_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime creationDate;

    @Column(name = "last_update_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    public void delete() {
        this.deleted = true;
    }

}
