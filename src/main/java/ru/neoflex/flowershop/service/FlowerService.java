package ru.neoflex.flowershop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.flowershop.entity.Flower;
import ru.neoflex.flowershop.repository.FlowerRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlowerService {

    private final FlowerRepository flowerRepository;

    public List<Flower> findAll(){
        List<Flower> flowers =  flowerRepository.findAll();
        if(flowers.isEmpty()) {
            throw new EntityNotFoundException ("Flowers not found");
        }
        return flowers;
    }

    public List<Flower> findAllByName(String name){
        List<Flower> flowers = flowerRepository.findAllByName(name);
        if(flowers.isEmpty()) {
            throw new EntityNotFoundException ("Flowers with name " + name + " not found");
        }
        return flowers;
    }

    public List<Flower> findAllByCost(BigDecimal cost){
        List<Flower> flowers = flowerRepository.findAllByCost(cost);
        if(flowers.isEmpty()) {
            throw new EntityNotFoundException ("Flowers with cost " + cost + " not found");
        }
        return flowers;
    }

    public Flower insertFlower(Flower flower){
        return flowerRepository.saveAndFlush(flower);
    }

    public Flower updateFlowerName(Long id, String name){
        Optional<Flower> flower = flowerRepository.findById(id);
        if (flower.isEmpty()) {
            throw new EntityNotFoundException ("Flower with id " + id + " not found");
        }
        flower.get().setName(name);
        return flowerRepository.save(flower.get());
    }

    public Flower updateFlowerCost(Long id, BigDecimal cost){
        Optional<Flower> flower = flowerRepository.findById(id);
        if (flower.isEmpty()) {
            throw new EntityNotFoundException ("Flower with id " + id + " not found");
        }
        flower.get().setCost(cost);
        return flowerRepository.save(flower.get());
    }

    public Flower updateFlowerDescription(Long id, String description){
        Optional<Flower> flower = flowerRepository.findById(id);
        if (flower.isEmpty()) {
            throw new EntityNotFoundException ("Flower with id " + id + " not found");
        }
        flower.get().setDescription(description);
        return flowerRepository.save(flower.get());
    }
}
