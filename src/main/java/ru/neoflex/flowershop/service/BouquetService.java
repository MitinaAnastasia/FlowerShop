package ru.neoflex.flowershop.service;

import ru.neoflex.flowershop.dto.BouquetDTO;
import java.math.BigDecimal;
import java.util.List;

public interface BouquetService {
    List<BouquetDTO> findAll();
    List<BouquetDTO> findAllByName(String name);
    List<BouquetDTO> findAllByCost(BigDecimal cost);
    List<BouquetDTO> findAllByFlowerId(Long id);
    BouquetDTO insertBouquet(BouquetDTO bouquetDTO);
    BouquetDTO updateBouquet(BouquetDTO bouquetDTO);
    void deleteBouquet(Long id);
}
