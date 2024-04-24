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
        bouquets.stream().findFirst().orElseThrow(()->
                new EntityNotFoundException(ErrorConstant.BOUQUETS_NOT_FOUND_MESSAGE)
        );
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByName(String name){
        List<Bouquet> bouquets = bouquetRepository.findAllByName(name);
        bouquets.stream().findFirst().orElseThrow(()->
                new EntityNotFoundException(StringUtils.join(ErrorConstant.BOUQUETS_WITH_NAME_NOT_FOUND_MESSAGE, name))
        );
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByCost(BigDecimal cost) {
        List<Bouquet> bouquets = bouquetRepository.findAllByCost(cost);
        bouquets.stream().findFirst().orElseThrow(() ->
                new EntityNotFoundException(StringUtils.join(ErrorConstant.BOUQUETS_WITH_COST_NOT_FOUND_MESSAGE, cost))
        );
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByFlowerId(Long id){
        List<Bouquet> bouquets = bouquetRepository.findAllByFlowerId(id);
        bouquets.stream().findFirst().orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.BOUQUETS_WiTH_FLOWERS_WITH_ID_NOT_FOUND_MESSAGE, id))
        );
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
    public BouquetDTO updateBouquetName(Long id, String name){
        Bouquet bouquet = bouquetRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.BOUQUET_WITH_ID_NOT_FOUND_MESSAGE, id))
        );
        bouquet.setName(name);
        return bouquetMapper.fromBouquetToBouquetDTO(bouquetRepository.save(bouquet));
    }

    @Override
    @Transactional
    public BouquetDTO updateBouquetCost(Long id, BigDecimal cost){
        Bouquet bouquet = bouquetRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.BOUQUET_WITH_ID_NOT_FOUND_MESSAGE, id))
        );
        bouquet.setCost(cost);
        return bouquetMapper.fromBouquetToBouquetDTO(bouquetRepository.save(bouquet));
    }

    @Override
    public void deleteBouquet(BouquetDTO bouquetDTO){
        Bouquet bouquet = bouquetMapper.fromBouquetDTOToBouquet(bouquetDTO);
        bouquetRepository.delete(bouquet);
    }
}
