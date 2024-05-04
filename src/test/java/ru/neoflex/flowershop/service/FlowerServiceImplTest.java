package ru.neoflex.flowershop.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.flowershop.dto.FlowerDTO;
import ru.neoflex.flowershop.entity.Flower;
import ru.neoflex.flowershop.entity.Provider;
import ru.neoflex.flowershop.entity.Season;
import ru.neoflex.flowershop.mapper.FlowerMapper;
import ru.neoflex.flowershop.repository.FlowerRepository;
import ru.neoflex.flowershop.service.impl.FlowerServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FlowerServiceImplTest {
    @InjectMocks
    private FlowerServiceImpl flowerService;
    @Mock
    private FlowerRepository flowerRepository;
    @Mock
    private FlowerMapper flowerMapper;

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

        Flower flowerIrisLilac = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(110),
                40, null, 14, season, provider, null);

        FlowerDTO flowerDTOIrisLilac = dataFlowerDTO(1L, "ирис сиреневый", BigDecimal.valueOf(110),
                40,14, season, provider);

        Flower flowerIrisYellow = new Flower(2L, "ирис желтый", BigDecimal.valueOf(110),
                45, null, 14, season, provider, null);

        FlowerDTO flowerDTOIrisYellow = dataFlowerDTO(2L, "ирис желтый", BigDecimal.valueOf(110),
                45, 14, season, provider);

        List<Flower> flowers = List.of(flowerIrisLilac, flowerIrisYellow);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOIrisYellow);

        Mockito.when(flowerRepository.findAll()).thenReturn(flowers);
        Mockito.when(flowerMapper.fromListFlowerToListFlowerDTO(flowers)).thenReturn(flowerDTOS);

        List<FlowerDTO> result = flowerService.findAll();
        Assertions.assertEquals(flowerDTOS, result);
    }

    @Test
    void testFindAllByName_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("ирисы по рф");

        String name = "ирис сиреневый";

        Flower flowerIrisLilac = new Flower(1L, name, BigDecimal.valueOf(110),
                40, null, 14, season, provider, null);

        FlowerDTO flowerDTOIrisLilac = dataFlowerDTO(1L, name, BigDecimal.valueOf(110),
                40,14, season, provider);

        Flower flowerIrisLilacAnother = new Flower(2L, name, BigDecimal.valueOf(120),
                45, null, 14, season, provider, null);

        FlowerDTO flowerDTOIrisLilacAnother = dataFlowerDTO(2L, name, BigDecimal.valueOf(120),
                45,14, season, provider);

        List<Flower> flowers = List.of(flowerIrisLilac, flowerIrisLilacAnother);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOIrisLilacAnother);

        Mockito.when(flowerRepository.findAllByName(name)).thenReturn(flowers);
        Mockito.when(flowerMapper.fromListFlowerToListFlowerDTO(flowers)).thenReturn(flowerDTOS);

        List<FlowerDTO> result = flowerService.findAllByName(name);
        Assertions.assertEquals(flowerDTOS, result);
    }

    @Test
    void testFindAllByCost_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("цветы оптом");

        BigDecimal cost = BigDecimal.valueOf(110);

        Flower flowerIrisRed = new Flower(1L, "ирис бордовый", cost,
                40, null, 14, season, provider, null);

        FlowerDTO flowerDTOIrisRed = dataFlowerDTO(1L, "ирис бордовый", cost,
                40,14, season, provider);

        Flower flowerNarcissusWhite = new Flower(2L, "нарцисс белый", cost,
                40, null, 7, season, provider, null);

        FlowerDTO flowerDTONarcissusWhite = dataFlowerDTO(2L, "нарцисс белый", cost,
                40,7, season, provider);

        List<Flower> flowers = List.of(flowerIrisRed, flowerNarcissusWhite);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisRed, flowerDTONarcissusWhite);

        Mockito.when(flowerRepository.findAllByCost(cost)).thenReturn(flowers);
        Mockito.when(flowerMapper.fromListFlowerToListFlowerDTO(flowers)).thenReturn(flowerDTOS);

        List<FlowerDTO> result = flowerService.findAllByCost(cost);
        Assertions.assertEquals(flowerDTOS, result);
    }

    @Test
    void testInsertFlower_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("цветы оптом");

        Flower flowerIrisRed = new Flower(1L, "ирис бордовый", BigDecimal.valueOf(110),
                40, null, 14, season, provider, null);

        FlowerDTO flowerDTOIrisRed = dataFlowerDTO(1L, "ирис бордовый", BigDecimal.valueOf(110),
                40,14, season, provider);

        Mockito.when(flowerMapper.fromFlowerDTOToFlower(flowerDTOIrisRed)).thenReturn(flowerIrisRed);
        Mockito.when(flowerMapper.fromFlowerToFlowerDTO(flowerIrisRed)).thenReturn(flowerDTOIrisRed);
        Mockito.when(flowerRepository.saveAndFlush(flowerIrisRed)).thenReturn(flowerIrisRed);

        FlowerDTO result = flowerService.insertFlower(flowerDTOIrisRed);
        Assertions.assertEquals(flowerDTOIrisRed, result);
    }

    @Test
    void testUpdateFlower_Success(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("цветы оптом");

        Flower flowerIrisRed = new Flower(1L, "ирис бордовый", BigDecimal.valueOf(110),
                40, null, 14, season, provider, null);

        FlowerDTO flowerDTOIrisRedUpdate = dataFlowerDTO(1L, "ирис бордовый", BigDecimal.valueOf(110),
                45,14, season, provider);

        Mockito.when(flowerRepository.findById(flowerDTOIrisRedUpdate.getId())).thenReturn(Optional.of(flowerIrisRed));
        flowerIrisRed.setHeight(45);
        Mockito.when(flowerRepository.save(flowerIrisRed)).thenReturn(flowerIrisRed);
        Mockito.when(flowerMapper.fromFlowerToFlowerDTO(flowerIrisRed)).thenReturn(flowerDTOIrisRedUpdate);

        FlowerDTO result = flowerService.updateFlower(flowerDTOIrisRedUpdate);
        Assertions.assertEquals(flowerDTOIrisRedUpdate, result);
    }

    @Test
    void testUpdateFlower_NotFound(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("цветы оптом");

        FlowerDTO flowerDTOIrisRedUpdate = dataFlowerDTO(1L, "ирис бордовый", BigDecimal.valueOf(110),
                45,14, season, provider);

        Mockito.when(flowerRepository.findById(flowerDTOIrisRedUpdate.getId())).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(EntityNotFoundException.class, () -> flowerService.updateFlower(flowerDTOIrisRedUpdate));
    }
}
