package ru.neoflex.flowershop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.neoflex.flowershop.dto.FlowerDTO;
import ru.neoflex.flowershop.entity.Flower;
import ru.neoflex.flowershop.mapper.FlowerMapper;
import ru.neoflex.flowershop.repository.FlowerRepository;
import ru.neoflex.flowershop.service.FlowerService;
import ru.neoflex.flowershop.utils.ErrorConstant;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;
    private final FlowerMapper flowerMapper;

    @Override
    public List<FlowerDTO> findAll(){
        log.debug("FlowerServiceImpl.findAll - start of work");
        List<Flower> flowers = flowerRepository.findAll();
        log.debug("FlowerServiceImpl.findAll - all flowers {}", flowers);
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    public List<FlowerDTO> findAllByName(String name){
        log.debug("FlowerServiceImpl.findAllByName - input data: name {}", name);
        List<Flower> flowers = flowerRepository.findAllByName(name);
        log.debug("FlowerServiceImpl.findAllByName - all flowers by name {}", flowers);
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    public List<FlowerDTO> findAllByCost(BigDecimal cost){
        log.debug("FlowerServiceImpl.findAllByCost - input data: cost {}", cost);
        List<Flower> flowers = flowerRepository.findAllByCost(cost);
        log.debug("FlowerServiceImpl.findAllByCost - all flowers by cost {}", flowers);
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    @Transactional
    public FlowerDTO insertFlower(FlowerDTO flowerDTO){
        log.debug("FlowerServiceImpl.insertFlower - input data: flowerDTO {}", flowerDTO);
        Flower flower = flowerMapper.fromFlowerDTOToFlower(flowerDTO);
        log.debug("FlowerServiceImpl.insertFlower - flower {}", flower);
        return flowerMapper.fromFlowerToFlowerDTO(flowerRepository.saveAndFlush(flower));
    }

    @Override
    @Transactional
    public FlowerDTO updateFlower(FlowerDTO flowerDTO){
        log.debug("FlowerServiceImpl.updateFlower - input data: flowerDTO {}", flowerDTO);
        Flower flower = flowerRepository.findById(flowerDTO.getId()).orElseThrow(()-> {
            log.warn("FlowerServiceImpl.updateFlower - not found by id");
            return new EntityNotFoundException(StringUtils.join(ErrorConstant.FLOWER_WITH_ID_NOT_FOUND_MESSAGE, flowerDTO.getId()));
        });
        flowerMapper.updateFlower(flowerDTO, flower);
        log.debug("FlowerServiceImpl.updateFlower - flower {}", flower);
        return flowerMapper.fromFlowerToFlowerDTO(flowerRepository.save(flower));
    }
}
