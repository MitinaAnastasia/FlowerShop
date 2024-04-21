package ru.neoflex.flowershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.flowershop.entity.Bouquet;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, Long> {
    List<Bouquet> findAllByName(String name);
    List<Bouquet> findAllByCost(BigDecimal cost);
    List<Bouquet> findAllByFlowerId(Long id);
}
