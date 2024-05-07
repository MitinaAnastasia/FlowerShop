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
import ru.neoflex.flowershop.dto.FlowerDTO;
import ru.neoflex.flowershop.entity.Provider;
import ru.neoflex.flowershop.entity.Season;
import ru.neoflex.flowershop.service.FlowerService;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FlowerControllerTest {
    @InjectMocks
    private FlowerController flowerController;
    @Mock
    private FlowerService flowerService;

    public Season season;
    public Provider provider;
    private FlowerDTO flowerDTOIrisLilac;
    private FlowerDTO flowerDTOAnother;

    @BeforeAll
    public static void prepareDataSeasonAndProvider(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("цветы по рф");
    }

    @BeforeEach
    public void prepareDataFlowerDTO(){
        flowerDTOIrisLilac= new FlowerDTO();
        flowerDTOIrisLilac.setId(1L);
        flowerDTOIrisLilac.setName("ирис сиреневый");
        flowerDTOIrisLilac.setCost(BigDecimal.valueOf(110));
        flowerDTOIrisLilac.setHeight(40);
        flowerDTOIrisLilac.setPeriodInWater(14);
        flowerDTOIrisLilac.setSeason(season);
        flowerDTOIrisLilac.setProvider(provider);

        flowerDTOAnother= new FlowerDTO();
        flowerDTOAnother.setId(2L);
        flowerDTOAnother.setName("ирис сиреневый");
        flowerDTOAnother.setCost(BigDecimal.valueOf(110));
        flowerDTOAnother.setHeight(45);
        flowerDTOAnother.setPeriodInWater(14);
        flowerDTOAnother.setSeason(season);
        flowerDTOAnother.setProvider(provider);
    }


    @Test
    void testFindAll_Success(){
        flowerDTOAnother.setName("ирис желтый");

        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOAnother);

        Mockito.when(flowerService.findAll()).thenReturn(flowerDTOS);

        ResponseEntity<List<FlowerDTO>> result = flowerController.findAll();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<FlowerDTO> actual = result.getBody();
        List<FlowerDTO> expected = flowerDTOS;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testFindAllByName_Success(){
        String name = "ирис сиреневый";

        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOAnother);

        Mockito.when(flowerService.findAllByName(name)).thenReturn(flowerDTOS);

        ResponseEntity<List<FlowerDTO>> result = flowerController.findAllByName(name);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<FlowerDTO> actual = result.getBody();
        List<FlowerDTO> expected = flowerDTOS;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testFindAllByCost_Success(){
        BigDecimal cost = BigDecimal.valueOf(110);
        flowerDTOAnother.setName("нарцисс белый");

        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOAnother);

        Mockito.when(flowerService.findAllByCost(cost)).thenReturn(flowerDTOS);

        ResponseEntity<List<FlowerDTO>> result = flowerController.findAllByCost(cost);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<FlowerDTO> actual = result.getBody();
        List<FlowerDTO> expected = flowerDTOS;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testCreateFlower_Success(){
        Mockito.when(flowerService.insertFlower(flowerDTOIrisLilac)).thenReturn(flowerDTOIrisLilac);

        ResponseEntity<FlowerDTO> result = flowerController.createFlower(flowerDTOIrisLilac);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        FlowerDTO actual = result.getBody();
        FlowerDTO expected = flowerDTOIrisLilac;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateFlower_Success(){
        Mockito.when(flowerService.updateFlower(flowerDTOIrisLilac)).thenReturn(flowerDTOIrisLilac);

        ResponseEntity<FlowerDTO> result = flowerController.updateFlower(flowerDTOIrisLilac);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        FlowerDTO actual = result.getBody();
        FlowerDTO expected = flowerDTOIrisLilac;
        Assertions.assertEquals(expected, actual);
    }

    @Test()
    void testUpdateFlower_NotFound(){
        Mockito.when(flowerService.updateFlower(flowerDTOIrisLilac)).thenThrow(new EntityNotFoundException());
        Assertions.assertThrows(EntityNotFoundException.class, () -> flowerController.updateFlower(flowerDTOIrisLilac));
    }
}
