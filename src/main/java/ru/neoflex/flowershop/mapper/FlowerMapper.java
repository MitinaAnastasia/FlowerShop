package ru.neoflex.flowershop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.neoflex.flowershop.dto.FlowerDTO;
import ru.neoflex.flowershop.entity.Flower;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlowerMapper {

    Flower fromFlowerDTOToFlower(FlowerDTO flowerDTO);

    FlowerDTO fromFlowerToFlowerDTO(Flower flower);
    List<FlowerDTO> fromListFlowerToListFlowerDTO(List<Flower> flower);

    void updateFlower(FlowerDTO flowerDTO, @MappingTarget Flower flower);

}
