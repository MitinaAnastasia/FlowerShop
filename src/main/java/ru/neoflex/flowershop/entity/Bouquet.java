package ru.neoflex.flowershop.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bouquet")
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bouquet_id", nullable = false)
    private Long id;

    @Column(name = "bouquet_name", nullable = false)
    private String name;

    @Column(name = "number_of_flowers", nullable = false)
    private Integer numberOfFlowers;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "flower_id_fk", nullable = false)
    private Flower flower;
}
