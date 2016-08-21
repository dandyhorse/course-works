package org.lenteam.colmen.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Anton_Solovev
 * @since 8/21/2016
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column
    private String name;

    public PersonEntity(String name) {
        this.name = name;
    }
}
