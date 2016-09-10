package org.lenteam.colmen.models;

import lombok.EqualsAndHashCode;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
@EqualsAndHashCode
public class Site {

    private Long id;

    private String name;

    public Site(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
