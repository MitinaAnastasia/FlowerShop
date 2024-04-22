package ru.neoflex.flowershop.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.neoflex.flowershop.dto.FlowerDTO;
import ru.neoflex.flowershop.entity.Flower;
import ru.neoflex.flowershop.service.FlowerService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/flower")
public class FlowerController {

    private final FlowerService flowerService;

    @GetMapping("/all")
    public ResponseEntity<List<Flower>> findAll() {
        return ResponseEntity.ok().body(flowerService.findAll());
    }

    @GetMapping("/find-name")
    public ResponseEntity<List<Flower>> findAllByName(@RequestParam
        @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
        @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
        String name) {
        return ResponseEntity.ok().body(flowerService.findAllByName(name));
    }

    @GetMapping("/find-cost")
    public ResponseEntity<List<Flower>> findAllByCost(@RequestParam
        @DecimalMin(message = "Стоимость цветка не должна быть меньше 50 рублей", value = "50")
        @DecimalMax(message = "Стоимость цветка не должна быть больше 250 рублей", value = "250") BigDecimal cost) {
        return ResponseEntity.ok().body(flowerService.findAllByCost(cost));
    }

    @PostMapping("/create")
    public ResponseEntity<Flower> createFlower(@Valid @RequestBody FlowerDTO flowerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flowerService.insertFlower(toEntity(flowerDTO)));
    }

    @PutMapping("/update-name")
    public ResponseEntity<Flower> updateFlowerName(@RequestParam Long id, @RequestParam
    @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
    @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
    String name){
        return ResponseEntity.status(HttpStatus.CREATED).body(flowerService.updateFlowerName(id, name));
    }

    @PutMapping("/update-cost")
    public ResponseEntity<Flower> updateFlowerCost(@RequestParam Long id, @RequestParam
    @DecimalMin(message = "Стоимость цветка не должна быть меньше 50 рублей", value = "50")
    @DecimalMax(message = "Стоимость цветка не должна быть больше 250 рублей", value = "250") BigDecimal cost){
        return ResponseEntity.status(HttpStatus.CREATED).body(flowerService.updateFlowerCost(id, cost));
    }

    @PutMapping("/update-description")
    public ResponseEntity<Flower> updateFlowerDescription(@RequestParam Long id, @RequestParam String description){
        return ResponseEntity.status(HttpStatus.CREATED).body(flowerService.updateFlowerDescription(id, description));
    }

    private Flower toEntity(FlowerDTO flowerDTO) {
        Flower flower = new Flower();
        flower.setId(flowerDTO.getId());
        flower.setName(flowerDTO.getName());
        flower.setCost(flowerDTO.getCost());
        flower.setHeight(flowerDTO.getHeight());
        flower.setDescription(flowerDTO.getDescription());
        flower.setPeriodInWater(flowerDTO.getPeriodInWater());
        flower.setSeason(flowerDTO.getSeason());
        flower.setProvider(flowerDTO.getProvider());
        flower.setAdditives(flowerDTO.getAdditives());
        return flower;
    }
}
