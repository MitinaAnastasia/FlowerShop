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
@Table(name = "additive")
public class Additive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additive_id", nullable = false)
    private Long additiveId;

    @Column(name = "additive_name", nullable = false)
    private String additiveName;

    @ManyToMany
    @JoinTable(name = "additive_flower",
            joinColumns = @JoinColumn(name = "additive_id_fk", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "flower_id_fk", nullable = false))
    private Set<Flower> flowers = new HashSet<>();
}
