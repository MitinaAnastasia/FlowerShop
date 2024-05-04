package ru.neoflex.flowershop.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.flowershop.dto.BouquetDTO;
import ru.neoflex.flowershop.entity.Bouquet;
import ru.neoflex.flowershop.entity.Flower;
import ru.neoflex.flowershop.entity.Provider;
import ru.neoflex.flowershop.entity.Season;
import ru.neoflex.flowershop.mapper.BouquetMapper;
import ru.neoflex.flowershop.repository.BouquetRepository;
import ru.neoflex.flowershop.service.impl.BouquetServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BouquetServiceImplTest {

    @InjectMocks
    private BouquetServiceImpl bouquetService;
    @Mock
    private BouquetRepository bouquetRepository;
    @Mock
    private BouquetMapper bouquetMapper;

    BouquetDTO dataFlowerDTO(Long id, String name, Integer numberOfFlowers, BigDecimal cost, Flower flower) {
        BouquetDTO bouquetDTO = new BouquetDTO();
        bouquetDTO.setId(id);
        bouquetDTO.setName(name);
        bouquetDTO.setNumberOfFlowers(numberOfFlowers);
        bouquetDTO.setCost(cost);
        bouquetDTO.setFlower(flower);
        return bouquetDTO;
    }

    @Test
    void testFindAll_Success() {
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        Bouquet bouquetFairyTale = new Bouquet(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Bouquet bouquetFairyTaleSmall = new Bouquet(2L, "Сказка малая", 3, BigDecimal.valueOf(300), flower);
        BouquetDTO bouquetDTOFairyTaleSmall = dataFlowerDTO(2L, "Сказка малая", 3, BigDecimal.valueOf(300), flower);

        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleSmall);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetRepository.findAll()).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> result = bouquetService.findAll();
        Assertions.assertEquals(bouquetDTOList, result);
    }

    @Test
    void testFindAllByName_Success() {
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        String name = "Сказка";

        Bouquet bouquetFairyTale = new Bouquet(1L, name, 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, name, 11, BigDecimal.valueOf(1100), flower);

        Bouquet bouquetFairyTaleSmall = new Bouquet(2L, name, 3, BigDecimal.valueOf(300), flower);
        BouquetDTO bouquetDTOFairyTaleSmall = dataFlowerDTO(2L, name, 3, BigDecimal.valueOf(300), flower);

        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleSmall);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetRepository.findAllByName(name)).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> result = bouquetService.findAllByName(name);
        Assertions.assertEquals(bouquetDTOList, result);
    }

    @Test
    void testFindAllByCost_Success() {
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        Flower flowerAnother = new Flower(2L, "ирис желтый", BigDecimal.valueOf(100),
                45, null, 14, season, provider, null);
        BigDecimal cost = BigDecimal.valueOf(1100);

        Bouquet bouquetFairyTale = new Bouquet(1L, "Сказка", 11, cost, flower);
        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, cost, flower);

        Bouquet bouquetFairyTaleYellow = new Bouquet(2L, "Сказка желтая", 11, cost, flowerAnother);
        BouquetDTO bouquetDTOFairyTaleYellow = dataFlowerDTO(2L, "Сказка желтая", 11, cost, flowerAnother);

        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleYellow);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleYellow);

        Mockito.when(bouquetRepository.findAllByCost(cost)).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> result = bouquetService.findAllByCost(cost);
        Assertions.assertEquals(bouquetDTOList, result);
    }
    @Test
    void testFindAllByFlowerId_Success() {
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        Bouquet bouquetFairyTale = new Bouquet(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Bouquet bouquetFairyTaleSmall = new Bouquet(2L, "Сказка малая", 3, BigDecimal.valueOf(300), flower);
        BouquetDTO bouquetDTOFairyTaleSmall = dataFlowerDTO(2L, "Сказка малая", 3, BigDecimal.valueOf(300), flower);

        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleSmall);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetRepository.findAllByFlowerId(flower.getId())).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> result = bouquetService.findAllByFlowerId(flower.getId());
        Assertions.assertEquals(bouquetDTOList, result);
    }

    @Test
    void testInsertBouquet_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");
        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        Bouquet bouquetFairyTale = new Bouquet(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Mockito.when(bouquetMapper.fromBouquetDTOToBouquet(bouquetDTOFairyTale)).thenReturn(bouquetFairyTale);
        Mockito.when(bouquetMapper.fromBouquetToBouquetDTO(bouquetFairyTale)).thenReturn(bouquetDTOFairyTale);
        Mockito.when(bouquetRepository.saveAndFlush(bouquetFairyTale)).thenReturn(bouquetFairyTale);

        BouquetDTO result = bouquetService.insertBouquet(bouquetDTOFairyTale);
        Assertions.assertEquals(bouquetDTOFairyTale, result);
    }

    @Test
    void testUpdateBouquet_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");
        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        Bouquet bouquetFairyTale = new Bouquet(1L, "Сказка сиреневая", 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTaleUpdate = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Mockito.when(bouquetRepository.findById(bouquetDTOFairyTaleUpdate.getId())).thenReturn(Optional.of(bouquetFairyTale));
        bouquetFairyTale.setName("Сказка");
        Mockito.when(bouquetRepository.save(bouquetFairyTale)).thenReturn(bouquetFairyTale);
        Mockito.when(bouquetMapper.fromBouquetToBouquetDTO(bouquetFairyTale)).thenReturn(bouquetDTOFairyTaleUpdate);

        BouquetDTO result = bouquetService.updateBouquet(bouquetDTOFairyTaleUpdate);
        Assertions.assertEquals(bouquetDTOFairyTaleUpdate, result);
    }

    @Test
    void testUpdateBouquet_NotFound(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");
        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        BouquetDTO bouquetDTOFairyTaleUpdate = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Mockito.when(bouquetRepository.findById(bouquetDTOFairyTaleUpdate.getId())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> bouquetService.updateBouquet(bouquetDTOFairyTaleUpdate));
    }

    @Test
    void testDeleteBouquet_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");
        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        Long id = 1L;
        Bouquet bouquetFairyTale = new Bouquet(id, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Mockito.when(bouquetRepository.findById(id)).thenReturn(Optional.of(bouquetFairyTale));

        bouquetService.deleteBouquet(id);

        Mockito.verify(bouquetRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void testDeleteBouquet_NotFound(){
        Long id = 10L;

        Mockito.when(bouquetRepository.findById(id)).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(EntityNotFoundException.class, () -> bouquetService.deleteBouquet(id));
    }
}
