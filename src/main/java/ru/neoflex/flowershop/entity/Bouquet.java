package ru.neoflex.flowershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bouquet")
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bouquet_id", nullable = false)
    private Long bouquetId;

    @Column(name = "bouquet_name", nullable = false)
    private String bouquetName;

    @Column(name = "number_of_flowers", nullable = false)
    private int numberOfFlowers;

    @Column(name = "cost", nullable = false)
    private double cost;

    @ManyToOne
    @JoinColumn(name = "flower_id_fk", nullable = false)
    private Flower flower;
}
