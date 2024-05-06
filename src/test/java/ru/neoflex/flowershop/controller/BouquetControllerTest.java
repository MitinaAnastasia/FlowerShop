package ru.neoflex.flowershop.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    static Flower flower;
    static Flower flowerAnother;
    BouquetDTO bouquetDTOFairyTale;
    BouquetDTO bouquetDTOFairyTaleSmall;
    static BouquetDTO bouquetDTOFairyTaleYellow;
    @BeforeAll
    static void prepareDataFlower(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        flower = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);
        flowerAnother = new Flower(2L, "ирис желтый", BigDecimal.valueOf(100),
                40, null, 14, season, provider, null);
    }

    @BeforeAll
    static void prepareDataFairyTaleYellow(){
        bouquetDTOFairyTaleYellow = new BouquetDTO();
        bouquetDTOFairyTaleYellow.setId(2L);
        bouquetDTOFairyTaleYellow.setName("Сказка желтая");
        bouquetDTOFairyTaleYellow.setNumberOfFlowers(11);
        bouquetDTOFairyTaleYellow.setCost(BigDecimal.valueOf(1100));
        bouquetDTOFairyTaleYellow.setFlower(flowerAnother);
    }

    @BeforeEach
    void prepareDataBouquetDTO(){
        bouquetDTOFairyTale = new BouquetDTO();
        bouquetDTOFairyTale.setId(1L);
        bouquetDTOFairyTale.setName("Сказка");
        bouquetDTOFairyTale.setNumberOfFlowers(11);
        bouquetDTOFairyTale.setCost(BigDecimal.valueOf(1100));
        bouquetDTOFairyTale.setFlower(flower);

        bouquetDTOFairyTaleSmall = new BouquetDTO();
        bouquetDTOFairyTaleSmall.setId(2L);
        bouquetDTOFairyTaleSmall.setName("Сказка");
        bouquetDTOFairyTaleSmall.setNumberOfFlowers(3);
        bouquetDTOFairyTaleSmall.setCost(BigDecimal.valueOf(300));
        bouquetDTOFairyTaleSmall.setFlower(flower);
    }

    @Test
    void testFindAll_Success(){
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetService.findAll()).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAll();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<BouquetDTO> actual = result.getBody();
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testFindAllByName_Success(){
        String name = "Сказка";

        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetService.findAllByName(name)).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAllByName(name);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<BouquetDTO> actual = result.getBody();
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testFindAllByCost_Success(){
        BigDecimal cost = BigDecimal.valueOf(1100);

        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleYellow);

        Mockito.when(bouquetService.findAllByCost(cost)).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAllByCost(cost);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<BouquetDTO> actual = result.getBody();
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testFindAllByFlowerId_Success(){
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetService.findAllByFlowerId(flower.getId())).thenReturn(bouquetDTOList);

        ResponseEntity<List<BouquetDTO>> result = bouquetController.findAllByFlowerId(flower.getId());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<BouquetDTO> actual = result.getBody();
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testCreateBouquet_Success(){
        Mockito.when(bouquetService.insertBouquet(bouquetDTOFairyTale)).thenReturn(bouquetDTOFairyTale);

        ResponseEntity<BouquetDTO> result = bouquetController.createBouquet(bouquetDTOFairyTale);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        BouquetDTO actual = result.getBody();
        BouquetDTO expected = bouquetDTOFairyTale;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateBouquet_Success(){
        Mockito.when(bouquetService.updateBouquet(bouquetDTOFairyTale)).thenReturn(bouquetDTOFairyTale);

        ResponseEntity<BouquetDTO> result = bouquetController.updateBouquet(bouquetDTOFairyTale);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        BouquetDTO actual = result.getBody();
        BouquetDTO expected = bouquetDTOFairyTale;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateBouquet_NotFound(){
        Mockito.when(bouquetService.updateBouquet(bouquetDTOFairyTale)).thenThrow(new EntityNotFoundException());
        Assertions.assertThrows(EntityNotFoundException.class, () -> bouquetController.updateBouquet(bouquetDTOFairyTale));
    }

    @Test
    void testDeleteBouquet_Success(){
        Long id = 1L;

        ResponseEntity<Void> result = bouquetController.deleteBouquet(id);
        Mockito.verify(bouquetService, Mockito.times(1)).deleteBouquet(id);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testDeleteBouquet_NotFound(){
        Long id = 10L;
        Mockito.doThrow(new EntityNotFoundException()).when(bouquetService).deleteBouquet(id);
        Assertions.assertThrows(EntityNotFoundException.class, () -> bouquetController.deleteBouquet(id));
    }
}
