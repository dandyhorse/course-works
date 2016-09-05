package org.lenteam.colmen.entities;

import javax.persistence.*;

/**
 * Created by Mickey on 05.09.2016.
 */
@Entity
@Table(name = "persons")
public class DemoPersonEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
