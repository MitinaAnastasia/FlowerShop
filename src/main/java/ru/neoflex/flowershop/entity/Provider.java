package ru.neoflex.flowershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id", nullable = false)
    private Long providerId;

    @Column(name = "provider_name", nullable = false)
    private String providerName;

    @Column(name = "provider_address")
    private String providerAddress;

    @Column(name = "provider_phone")
    private String providerPhone;
}
