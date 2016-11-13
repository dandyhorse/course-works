package ru.ssau.tourism.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
    @Column
    private LocalDate payDay;
    @Column
    private Integer amount;

    public Long getId() {
        return id;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public Integer getAmount() {
        return amount;
    }
}
