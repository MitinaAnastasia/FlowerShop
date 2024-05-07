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

    private static Flower flower;
    private static Flower flowerAnother;
    private Bouquet bouquetFairyTale;
    private Bouquet bouquetFairyTaleSmall;
    private static Bouquet bouquetFairyTaleYellow;
    private BouquetDTO bouquetDTOFairyTale;
    private BouquetDTO bouquetDTOFairyTaleSmall;
    private static BouquetDTO bouquetDTOFairyTaleYellow;
    @BeforeAll
    public static void prepareDataFlower(){
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
    public static void prepareDataFairyTaleYellow(){
        bouquetFairyTaleYellow = new Bouquet(
                2L, "Сказка желтая", 11, BigDecimal.valueOf(1100), flowerAnother);

        bouquetDTOFairyTaleYellow = new BouquetDTO();
        bouquetDTOFairyTaleYellow.setId(2L);
        bouquetDTOFairyTaleYellow.setName("Сказка желтая");
        bouquetDTOFairyTaleYellow.setNumberOfFlowers(11);
        bouquetDTOFairyTaleYellow.setCost(BigDecimal.valueOf(1100));
        bouquetDTOFairyTaleYellow.setFlower(flowerAnother);
    }

    @BeforeEach
    public void prepareDataBouquet(){
        bouquetFairyTale = new Bouquet(1L, "Сказка", 11, BigDecimal.valueOf(1100), flower);
        bouquetFairyTaleSmall = new Bouquet(2L, "Сказка", 3, BigDecimal.valueOf(300), flower);
    }

    @BeforeEach
    public void prepareDataBouquetDTO(){
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
    void testFindAll_Success() {
        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleSmall);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetRepository.findAll()).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> actual = bouquetService.findAll();
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testFindAllByName_Success() {
        String name = "Сказка";

        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleSmall);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetRepository.findAllByName(name)).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> actual = bouquetService.findAllByName(name);
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testFindAllByCost_Success() {
        BigDecimal cost = BigDecimal.valueOf(1100);

        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleYellow);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleYellow);

        Mockito.when(bouquetRepository.findAllByCost(cost)).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> actual = bouquetService.findAllByCost(cost);
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }
    @Test
    void testFindAllByFlowerId_Success() {
        List<Bouquet> bouquetList = List.of(bouquetFairyTale, bouquetFairyTaleSmall);
        List<BouquetDTO> bouquetDTOList = List.of(bouquetDTOFairyTale, bouquetDTOFairyTaleSmall);

        Mockito.when(bouquetRepository.findAllByFlowerId(flower.getId())).thenReturn(bouquetList);
        Mockito.when(bouquetMapper.fromListBouquetToListBouquetDTO(bouquetList)).thenReturn(bouquetDTOList);

        List<BouquetDTO> actual = bouquetService.findAllByFlowerId(flower.getId());
        List<BouquetDTO> expected = bouquetDTOList;

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals(2L, actual.get(1).getId());
        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    void testInsertBouquet_Success(){
        Mockito.when(bouquetMapper.fromBouquetDTOToBouquet(bouquetDTOFairyTale)).thenReturn(bouquetFairyTale);
        Mockito.when(bouquetMapper.fromBouquetToBouquetDTO(bouquetFairyTale)).thenReturn(bouquetDTOFairyTale);
        Mockito.when(bouquetRepository.saveAndFlush(bouquetFairyTale)).thenReturn(bouquetFairyTale);

        BouquetDTO actual = bouquetService.insertBouquet(bouquetDTOFairyTale);
        BouquetDTO expected = bouquetDTOFairyTale;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateBouquet_Success(){
        bouquetFairyTale.setName("Сказка сиреневая");

        Mockito.when(bouquetRepository.findById(bouquetDTOFairyTale.getId())).thenReturn(Optional.of(bouquetFairyTale));
        bouquetFairyTale.setName("Сказка");
        Mockito.when(bouquetRepository.save(bouquetFairyTale)).thenReturn(bouquetFairyTale);
        Mockito.when(bouquetMapper.fromBouquetToBouquetDTO(bouquetFairyTale)).thenReturn(bouquetDTOFairyTale);

        BouquetDTO actual = bouquetService.updateBouquet(bouquetDTOFairyTale);
        BouquetDTO expected = bouquetDTOFairyTale;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateBouquet_NotFound(){
        bouquetFairyTale.setName("Сказка сиреневая");

        Mockito.when(bouquetRepository.findById(bouquetDTOFairyTale.getId())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> bouquetService.updateBouquet(bouquetDTOFairyTale));
    }

    @Test
    void testDeleteBouquet_Success(){
        Long id = 1L;

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
