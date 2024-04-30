package ru.neoflex.flowershop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BouquetServiceImpl implements BouquetService {
    private final BouquetRepository bouquetRepository;
    private final BouquetMapper bouquetMapper;

    @Override
    public List<BouquetDTO> findAll(){
        log.debug("BouquetServiceImpl.findAll - start of work");
        List<Bouquet> bouquets = bouquetRepository.findAll();
        log.debug("BouquetServiceImpl.findAll - all bouquets {}", bouquets);
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByName(String name){
        log.debug("BouquetServiceImpl.findAllByName - input data: name {}", name);
        List<Bouquet> bouquets = bouquetRepository.findAllByName(name);
        log.debug("BouquetServiceImpl.findAllByName - all bouquets by name {}", bouquets);
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByCost(BigDecimal cost) {
        log.debug("BouquetServiceImpl.findAllByCost - input data: cost {}", cost);
        List<Bouquet> bouquets = bouquetRepository.findAllByCost(cost);
        log.debug("BouquetServiceImpl.findAllByCost - all bouquets by cost {}", bouquets);
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    public List<BouquetDTO> findAllByFlowerId(Long id){
        log.debug("BouquetServiceImpl.findAllByFlowerId - input data: id {}", id);
        List<Bouquet> bouquets = bouquetRepository.findAllByFlowerId(id);
        log.debug("BouquetServiceImpl.findAllByFlowerId - all bouquets by flower id {}", bouquets);
        return bouquetMapper.fromListBouquetToListBouquetDTO(bouquets);
    }

    @Override
    @Transactional
    public BouquetDTO insertBouquet(BouquetDTO bouquetDTO){
        log.debug("BouquetServiceImpl.insertBouquet - input data: bouquetDTO {}", bouquetDTO);
        Bouquet bouquet = bouquetMapper.fromBouquetDTOToBouquet(bouquetDTO);
        log.debug("BouquetServiceImpl.insertBouquet - bouquet {}", bouquet);
        return bouquetMapper.fromBouquetToBouquetDTO(bouquetRepository.saveAndFlush(bouquet));
    }

    @Override
    @Transactional
    public BouquetDTO updateBouquet(BouquetDTO bouquetDTO){
        log.debug("BouquetServiceImpl.updateBouquet - input data: bouquetDTO {}", bouquetDTO);
        Bouquet bouquet = bouquetRepository.findById(bouquetDTO.getId()).orElseThrow(()-> {
            log.warn("BouquetServiceImpl.updateBouquet - not found by id");
            return new EntityNotFoundException(StringUtils.join(ErrorConstant.BOUQUET_WITH_ID_NOT_FOUND_MESSAGE, bouquetDTO.getId()));
        });
        bouquetMapper.updateBouquet(bouquetDTO, bouquet);
        log.debug("BouquetServiceImpl.updateBouquet - bouquet {}", bouquet);
        return bouquetMapper.fromBouquetToBouquetDTO(bouquetRepository.save(bouquet));
    }

    @Override
    public void deleteBouquet(Long id){
        log.debug("BouquetServiceImpl.deleteBouquet - input data: id {}", id);
        bouquetRepository.findById(id).orElseThrow(()-> {
            log.warn("BouquetServiceImpl.deleteBouquet - not found by id");
            return new EntityNotFoundException(StringUtils.join(ErrorConstant.BOUQUET_WITH_ID_NOT_FOUND_MESSAGE, id));
        });
        bouquetRepository.deleteById(id);
        log.debug("BouquetServiceImpl.deleteBouquet - end of work");
    }
}
