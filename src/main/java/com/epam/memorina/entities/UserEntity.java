package com.epam.memorina.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * @author Solovev Anton
 * @since 26.07.2016.
 */
@Entity
@Table(name = "users")
public class UserEntity extends AbstractPersistable<Long> {

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public UserEntity() {
        super();
    }

    public UserEntity(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
