package org.lenteam.colmen.models;

import lombok.EqualsAndHashCode;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
@EqualsAndHashCode
public class Site {

    private Integer id;
    private String name;

    public Site(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
