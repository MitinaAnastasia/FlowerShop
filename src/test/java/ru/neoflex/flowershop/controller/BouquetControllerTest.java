package ru.neoflex.flowershop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.neoflex.flowershop.dto.BouquetDTO;
import ru.neoflex.flowershop.entity.Flower;
import ru.neoflex.flowershop.entity.Provider;
import ru.neoflex.flowershop.entity.Season;
import ru.neoflex.flowershop.service.BouquetService;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BouquetControllerTest {

    @InjectMocks
    private BouquetController bouquetController;
    @Mock
    private BouquetService bouquetService;

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
    void testFindAll_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTaleSmall = dataFlowerDTO(2L, "Сказка малая", 3, BigDecimal.valueOf(300), flower);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetService.findAll()).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAll();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(bouquetDTOList, result.getBody());
    }

    @Test
    void testFindAllByName_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        String name = "Сказка";

        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, name, 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTaleSmall = dataFlowerDTO(2L, name, 3, BigDecimal.valueOf(300), flower);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetService.findAllByName(name)).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAllByName(name);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(bouquetDTOList, result.getBody());
    }

    @Test
    void testFindAllByCost_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        Flower flowerAnother = new Flower(2L, "ирис желтый", BigDecimal.valueOf(100),
                45, null, 14, season, provider, null);
        BigDecimal cost = BigDecimal.valueOf(1100);

        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, cost, flower);
        BouquetDTO bouquetDTOFairyTaleYellow = dataFlowerDTO(2L, "Сказка желтая", 11, cost, flowerAnother);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleYellow);

        Mockito.when(bouquetService.findAllByCost(cost)).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAllByCost(cost);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(bouquetDTOList, result.getBody());
    }

    @Test
    void testFindAllByFlowerId_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);
        BouquetDTO bouquetDTOFairyTaleSmall = dataFlowerDTO(2L, "Сказка малая", 3, BigDecimal.valueOf(300), flower);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetService.findAllByFlowerId(flower.getId())).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAllByFlowerId(flower.getId());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(bouquetDTOList, result.getBody());
    }

    @Test
    void testCreateBouquet_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");
        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        BouquetDTO bouquetDTOFairyTale = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Mockito.when(bouquetService.insertBouquet(bouquetDTOFairyTale)).thenReturn(bouquetDTOFairyTale);

        ResponseEntity<BouquetDTO> result = bouquetController.createBouquet(bouquetDTOFairyTale);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(bouquetDTOFairyTale, result.getBody());
    }

    @Test
    void testUpdateBouquet_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");
        Flower flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);

        BouquetDTO bouquetDTOFairyTaleUpdate = dataFlowerDTO(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);

        Mockito.when(bouquetService.updateBouquet(bouquetDTOFairyTaleUpdate)).thenReturn(bouquetDTOFairyTaleUpdate);

        ResponseEntity<BouquetDTO> result = bouquetController.updateBouquet(bouquetDTOFairyTaleUpdate);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(bouquetDTOFairyTaleUpdate, result.getBody());
    }

    @Test
    void testDeleteBouquet_Success(){
        Long id = 1L;

        ResponseEntity<Void> result = bouquetController.deleteBouquet(id);
        Mockito.verify(bouquetService, Mockito.times(1)).deleteBouquet(id);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
