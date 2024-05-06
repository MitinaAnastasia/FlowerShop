package ru.neoflex.flowershop.service;

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

    Flower flowerIrisLilac;
    Flower flowerAnother;
    FlowerDTO flowerDTOIrisLilac;
    FlowerDTO flowerDTOAnother;
    Season season;
    Provider provider;

    @BeforeAll
    static void prepareDataSeasonAndProvider(){
        Season season = new Season();
        season.setName("весна");
        Provider provider = new Provider();
        provider.setName("цветы по рф");
    }

    @BeforeEach
    void prepareDataFlower() {
        flowerIrisLilac = new Flower(1L, "ирис сиреневый", BigDecimal.valueOf(110),
                40, null, 14, season, provider, null);

        flowerAnother = new Flower(2L, "ирис сиреневый", BigDecimal.valueOf(110),
                45, null, 14, season, provider, null);
    }

    @BeforeEach
    void prepareDataFlowerDTO(){
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
        flowerAnother.setName("ирис желтый");
        flowerDTOAnother.setName("ирис желтый");

        List<Flower> flowers = List.of(flowerIrisLilac, flowerAnother);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOAnother);

        Mockito.when(flowerRepository.findAll()).thenReturn(flowers);
        Mockito.when(flowerMapper.fromListFlowerToListFlowerDTO(flowers)).thenReturn(flowerDTOS);

        List<FlowerDTO> actual = flowerService.findAll();
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

        List<Flower> flowers = List.of(flowerIrisLilac, flowerAnother);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOAnother);

        Mockito.when(flowerRepository.findAllByName(name)).thenReturn(flowers);
        Mockito.when(flowerMapper.fromListFlowerToListFlowerDTO(flowers)).thenReturn(flowerDTOS);

        List<FlowerDTO> actual = flowerService.findAllByName(name);
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
        flowerAnother.setName("нарцисс белый");
        flowerDTOAnother.setName("нарцисс белый");

        List<Flower> flowers = List.of(flowerIrisLilac, flowerAnother);
        List<FlowerDTO> flowerDTOS = List.of(flowerDTOIrisLilac, flowerDTOAnother);

        Mockito.when(flowerRepository.findAllByCost(cost)).thenReturn(flowers);
        Mockito.when(flowerMapper.fromListFlowerToListFlowerDTO(flowers)).thenReturn(flowerDTOS);

        List<FlowerDTO> actual = flowerService.findAllByCost(cost);
        List<FlowerDTO> expected = flowerDTOS;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testInsertFlower_Success(){
        Mockito.when(flowerMapper.fromFlowerDTOToFlower(flowerDTOIrisLilac)).thenReturn(flowerIrisLilac);
        Mockito.when(flowerMapper.fromFlowerToFlowerDTO(flowerIrisLilac)).thenReturn(flowerDTOIrisLilac);
        Mockito.when(flowerRepository.saveAndFlush(flowerIrisLilac)).thenReturn(flowerIrisLilac);

        FlowerDTO actual = flowerService.insertFlower(flowerDTOIrisLilac);
        FlowerDTO expected = flowerDTOIrisLilac;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateFlower_Success(){
        flowerDTOAnother.setId(1L);

        Mockito.when(flowerRepository.findById(flowerDTOAnother.getId())).thenReturn(Optional.of(flowerIrisLilac));
        flowerIrisLilac.setHeight(45);
        Mockito.when(flowerRepository.save(flowerIrisLilac)).thenReturn(flowerIrisLilac);
        Mockito.when(flowerMapper.fromFlowerToFlowerDTO(flowerIrisLilac)).thenReturn(flowerDTOAnother);

        FlowerDTO actual = flowerService.updateFlower(flowerDTOAnother);
        FlowerDTO expected = flowerDTOAnother;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateFlower_NotFound(){
        flowerDTOAnother.setId(1L);

        Mockito.when(flowerRepository.findById(flowerDTOAnother.getId())).thenThrow(new EntityNotFoundException());

        Assertions.assertThrows(EntityNotFoundException.class, () -> flowerService.updateFlower(flowerDTOAnother));
    }
}
