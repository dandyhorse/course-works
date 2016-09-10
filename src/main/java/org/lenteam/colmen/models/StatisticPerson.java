package org.lenteam.colmen.models;

import lombok.EqualsAndHashCode;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
@EqualsAndHashCode
public class StatisticPerson extends Person {

    private Integer rank;

    public StatisticPerson(Long id, String name, Integer rank) {
        super(id, name);
        this.rank = rank;
    }

    public Integer getRank() {
        return rank;
    }
}
