package ru.neoflex.flowershop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.neoflex.flowershop.dto.BouquetDTO;
import ru.neoflex.flowershop.entity.Bouquet;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BouquetMapper {

    Bouquet fromBouquetDTOToBouquet(BouquetDTO bouquetDTO);

    BouquetDTO fromBouquetToBouquetDTO(Bouquet bouquet);
    List<BouquetDTO> fromListBouquetToListBouquetDTO(List<Bouquet> bouquet);

    void updateBouquet(BouquetDTO bouquetDTO, @MappingTarget Bouquet bouquet);
}
