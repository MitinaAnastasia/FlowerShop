package ru.neoflex.flowershop.service;

import ru.neoflex.flowershop.dto.FlowerDTO;
import java.math.BigDecimal;
import java.util.List;

public interface FlowerService {
    List<FlowerDTO> findAll();
    List<FlowerDTO> findAllByName(String name);
    List<FlowerDTO> findAllByCost(BigDecimal cost);
    FlowerDTO insertFlower(FlowerDTO flowerDTO);
    FlowerDTO updateFlowerName(Long id, String name);
    FlowerDTO updateFlowerCost(Long id, BigDecimal cost);
    FlowerDTO updateFlowerDescription(Long id, String description);
}
