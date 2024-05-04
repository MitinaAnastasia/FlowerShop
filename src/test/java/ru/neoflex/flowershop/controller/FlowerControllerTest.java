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

    FlowerDTO dataFlowerDTO(Long id, String name, BigDecimal cost, Integer height, Integer periodInWater, Season season, Provider provider){
        FlowerDTO flowerDTO= new FlowerDTO();
        flowerDTO.setId(id);
        flowerDTO.setName(name);
        flowerDTO.setCost(cost);
        flowerDTO.setHeight(height);
        flowerDTO.setPeriodInWater(periodInWater);
        flowerDTO.setSeason(season);
        flowerDTO.setProvider(provider);
        return flowerDTO;
    }

    @Test
    void testFindAll_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        FlowerDTO flowerDTOIrisLilac = dataFlowerDTO(1L, "ирис сиреневый", BigDecimal.valueOf(110),
                40,14, season, provider);
        FlowerDTO flowerDTOIrisYellow = dataFlowerDTO(2L, "ирис желтый", BigDecimal.valueOf(110),
                40,14, season, provider);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOIrisYellow);

        Mockito.when(flowerService.findAll()).thenReturn(flowerDTOS);

        ResponseEntity<List<FlowerDTO>> result = flowerController.findAll();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(flowerDTOS, result.getBody());
    }

    @Test
    void testFindAllByName_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        String name = "ирис сиреневый";

        FlowerDTO flowerDTOIrisLilac = dataFlowerDTO(1L, name, BigDecimal.valueOf(110),
                40,14, season, provider);
        FlowerDTO flowerDTOIrisLilacAnother = dataFlowerDTO(2L, name, BigDecimal.valueOf(120),
                45,14, season, provider);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOIrisLilacAnother);

        Mockito.when(flowerService.findAllByName(name)).thenReturn(flowerDTOS);

        ResponseEntity<List<FlowerDTO>> result = flowerController.findAllByName(name);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(flowerDTOS, result.getBody());
    }

    @Test
    void testFindAllByCost_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("цветы оптом");

        BigDecimal cost = BigDecimal.valueOf(110);

        FlowerDTO flowerDTOIrisRed = dataFlowerDTO(1L, "ирис бордовый", cost,
                40,14, season, provider);
        FlowerDTO flowerDTONarcissusWhite = dataFlowerDTO(2L, "нарцисс белый", cost,
                40,7, season, provider);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisRed, flowerDTONarcissusWhite);

        Mockito.when(flowerService.findAllByCost(cost)).thenReturn(flowerDTOS);

        ResponseEntity<List<FlowerDTO>> result = flowerController.findAllByCost(cost);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(flowerDTOS, result.getBody());
    }

    @Test
    void testCreateFlower_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        FlowerDTO flowerDTOIrisLilac = dataFlowerDTO(1L, "ирис сиреневый", BigDecimal.valueOf(110),
                40,14, season, provider);
        Mockito.when(flowerService.insertFlower(flowerDTOIrisLilac)).thenReturn(flowerDTOIrisLilac);

        ResponseEntity<FlowerDTO> result = flowerController.createFlower(flowerDTOIrisLilac);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(flowerDTOIrisLilac, result.getBody());
    }

    @Test
    void testUpdateFlower_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        FlowerDTO flowerDTOIrisLilacUpdate = dataFlowerDTO(1L, "ирис сиреневый", BigDecimal.valueOf(110),
                40,14, season, provider);
        Mockito.when(flowerService.updateFlower(flowerDTOIrisLilacUpdate)).thenReturn(flowerDTOIrisLilacUpdate);

        ResponseEntity<FlowerDTO> result = flowerController.updateFlower(flowerDTOIrisLilacUpdate);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(flowerDTOIrisLilacUpdate, result.getBody());
    }
}
