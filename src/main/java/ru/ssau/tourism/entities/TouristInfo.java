package ru.ssau.tourism.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TouristInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "tourist_id")
    private Tourist tourist;
    @Column
    private String passport;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private Integer phone_number;
    @Column
    private Integer index;

}
