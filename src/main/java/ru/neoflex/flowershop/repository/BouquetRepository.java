package ru.neoflex.flowershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.neoflex.flowershop.entity.Bouquet;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, Long> {
    List<Bouquet> findAllByName(String name);
    List<Bouquet> findAllByCost(BigDecimal cost);
    @Query(nativeQuery = true, value = "select * from bouquet b where b.flower_id_fk = :id")
    List<Bouquet> findAllByFlowerId(Long id);
}
