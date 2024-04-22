package ru.neoflex.flowershop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.flowershop.entity.Bouquet;
import ru.neoflex.flowershop.repository.BouquetRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BouquetService {

    private final BouquetRepository bouquetRepository;

    public List<Bouquet> findAll(){
        List<Bouquet> bouquets = bouquetRepository.findAll();
        if(bouquets.isEmpty()) {
            throw new EntityNotFoundException("Bouquets not found");
        }
        return bouquets;
    }

    public List<Bouquet> findAllByName(String name){
        List<Bouquet> bouquets = bouquetRepository.findAllByName(name);
        if(bouquets.isEmpty()) {
            throw new EntityNotFoundException ("Bouquets with name " + name + " not found");
        }
        return bouquets;
    }

    public List<Bouquet> findAllByCost(BigDecimal cost){
        List<Bouquet> bouquets = bouquetRepository.findAllByCost(cost);
        if(bouquets.isEmpty()) {
            throw new EntityNotFoundException ("Bouquets with cost " + cost + " not found");
        }
        return bouquets;
    }

    public List<Bouquet> findAllByFlowerId(Long id){
        List<Bouquet> bouquets = bouquetRepository.findAllByFlowerId(id);
        if(bouquets.isEmpty()) {
            throw new EntityNotFoundException ("Bouquets with flowers with id " + id + " not found");
        }
        return bouquets;
    }

    public Bouquet insertBouquet(Bouquet bouquet){
        return bouquetRepository.saveAndFlush(bouquet);
    }

    public Bouquet updateBouquetName(Long id, String name){
        Optional<Bouquet> bouquet = bouquetRepository.findById(id);
        if(bouquet.isEmpty()){
            throw new EntityNotFoundException ("Bouquet with id " + id + " not found");
        }
        bouquet.get().setName(name);
        return bouquetRepository.save(bouquet.get());
    }

    public Bouquet updateBouquetCost(Long id, BigDecimal cost){
        Optional<Bouquet> bouquet = bouquetRepository.findById(id);
        if(bouquet.isEmpty()){
            throw new EntityNotFoundException ("Bouquet with id " + id + " not found");
        }
        bouquet.get().setCost(cost);
        return bouquetRepository.save(bouquet.get());
    }

    public void deleteBouquet(Bouquet bouquet){
        bouquetRepository.delete(bouquet);
    }
}
