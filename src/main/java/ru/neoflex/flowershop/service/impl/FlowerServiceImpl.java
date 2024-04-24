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
        flowers.stream().findFirst().orElseThrow(()->
            new EntityNotFoundException (ErrorConstant.FLOWERS_NOT_FOUND_MESSAGE)
        );
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    public List<FlowerDTO> findAllByName(String name){
        List<Flower> flowers = flowerRepository.findAllByName(name);
        flowers.stream().findFirst().orElseThrow(()->
            new EntityNotFoundException (StringUtils.join(ErrorConstant.FLOWERS_WITH_NAME_NOT_FOUND_MESSAGE, name))
        );
        return flowerMapper.fromListFlowerToListFlowerDTO(flowers);
    }

    @Override
    public List<FlowerDTO> findAllByCost(BigDecimal cost){
        List<Flower> flowers = flowerRepository.findAllByCost(cost);
        flowers.stream().findFirst().orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.FLOWERS_WITH_COST_NOT_FOUND_MESSAGE, cost))
        );
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
    public FlowerDTO updateFlowerName(Long id, String name){
        Flower flower = flowerRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.FLOWER_WITH_ID_NOT_FOUND_MESSAGE, id))
        );
        flower.setName(name);
        return flowerMapper.fromFlowerToFlowerDTO(flowerRepository.save(flower));
    }

    @Override
    @Transactional
    public FlowerDTO updateFlowerCost(Long id, BigDecimal cost){
        Flower flower = flowerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.FLOWER_WITH_ID_NOT_FOUND_MESSAGE, id))
        );
        flower.setCost(cost);
        return flowerMapper.fromFlowerToFlowerDTO(flowerRepository.save(flower));
    }

    @Override
    @Transactional
    public FlowerDTO updateFlowerDescription(Long id, String description){
        Flower flower = flowerRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException (StringUtils.join(ErrorConstant.FLOWER_WITH_ID_NOT_FOUND_MESSAGE, id))
        );
        flower.setDescription(description);
        return flowerMapper.fromFlowerToFlowerDTO(flowerRepository.save(flower));
    }
}
