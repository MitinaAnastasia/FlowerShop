package ru.neoflex.flowershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flower")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flower_id", nullable = false)
    private Long flowerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "description")
    private String description;

    @Column(name = "period_in_water", nullable = false)
    private int periodInWater;

    @ManyToOne
    @JoinColumn(name = "season_id_fk", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "provider_id_fk", nullable = false)
    private Provider provider;

    @ManyToMany
    @JoinTable(name = "additive_flower",
            joinColumns = @JoinColumn(name = "flower_id_fk", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "additive_id_fk", nullable = false))
    private Set<Additive> additives = new HashSet<>();
}
