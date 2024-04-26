package ru.neoflex.flowershop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.neoflex.flowershop.dto.BouquetDTO;
import ru.neoflex.flowershop.entity.Bouquet;
import ru.neoflex.flowershop.mapper.BouquetMapper;
import ru.neoflex.flowershop.repository.BouquetRepository;
import ru.neoflex.flowershop.service.BouquetService;
import ru.neoflex.flowershop.utils.ErrorConstant;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BouquetServiceImpl implements BouquetService {
    private final BouquetRepository bouquetRepository;
    private final BouquetMapper bouquetMapper;

    @Override
    public List<BouquetDTO> findAll(){
        List<Bouquet> bouquets = bouquetRepository.findAll();
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByName(String name){
        List<Bouquet> bouquets = bouquetRepository.findAllByName(name);
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByCost(BigDecimal cost) {
        List<Bouquet> bouquets = bouquetRepository.findAllByCost(cost);
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByFlowerId(Long id){
        List<Bouquet> bouquets = bouquetRepository.findAllByFlowerId(id);
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    @Transactional
    public BouquetDTO insertBouquet(BouquetDTO bouquetDTO){
        Bouquet bouquet = bouquetMapper.fromBouquetDTOToBouquet(bouquetDTO);
        return bouquetMapper.fromBouquetToBouquetDTO(bouquetRepository.saveAndFlush(bouquet));
    }

    @Override
    @Transactional
    public BouquetDTO updateBouquet(BouquetDTO bouquetDTO){
        Bouquet bouquet = bouquetRepository.findById(bouquetDTO.getId()).orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.BOUQUET_WITH_ID_NOT_FOUND_MESSAGE, bouquetDTO.getId()))
        );
        bouquetMapper.updateBouquet(bouquetDTO, bouquet);
        return bouquetMapper.fromBouquetToBouquetDTO(bouquetRepository.save(bouquet));
    }

    @Override
    public void deleteBouquet(Long id){
        bouquetRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.BOUQUET_WITH_ID_NOT_FOUND_MESSAGE, id))
        );
        bouquetRepository.deleteById(id);
    }
}
