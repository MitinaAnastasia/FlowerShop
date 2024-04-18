package ru.neoflex.flowershop.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "description")
    private String description;

    @Column(name = "period_in_water", nullable = false)
    private Integer periodInWater;

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
