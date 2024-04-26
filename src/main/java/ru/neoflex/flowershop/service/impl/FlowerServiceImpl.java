package ru.neoflex.flowershop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;
    private final FlowerMapper flowerMapper;

    @Override
    public List<FlowerDTO> findAll(){
        List<Flower> flowers = flowerRepository.findAll();
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    public List<FlowerDTO> findAllByName(String name){
        List<Flower> flowers = flowerRepository.findAllByName(name);
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    public List<FlowerDTO> findAllByCost(BigDecimal cost){
        List<Flower> flowers = flowerRepository.findAllByCost(cost);
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    @Transactional
    public FlowerDTO insertFlower(FlowerDTO flowerDTO){
        Flower flower = flowerMapper.fromFlowerDTOToFlower(flowerDTO);
        return flowerMapper.fromFlowerToFlowerDTO(flowerRepository.saveAndFlush(flower));
    }

    @Override
    @Transactional
    public FlowerDTO updateFlower(FlowerDTO flowerDTO){
        Flower flower = flowerRepository.findById(flowerDTO.getId()).orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.FLOWER_WITH_ID_NOT_FOUND_MESSAGE, flowerDTO.getId()))
        );
        flowerMapper.updateFlower(flowerDTO, flower);
        return flowerMapper.fromFlowerToFlowerDTO(flowerRepository.save(flower));
    }
}
