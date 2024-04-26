package ru.neoflex.flowershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.flowershop.entity.Flower;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findAllByName(String name);
    List<Flower> findAllByCost(BigDecimal cost);
}
