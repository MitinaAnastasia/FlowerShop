package ru.neoflex.flowershop.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<FlowerDTO>> findAll() {
        List<FlowerDTO> flowerDTOS = flowerService.findAll();
        return ResponseEntity.ok(flowerDTOS);
    }

    @GetMapping("/find-name")
    public ResponseEntity<List<FlowerDTO>> findAllByName(@RequestParam
        @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
        @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
        String name) {
        List<FlowerDTO> flowerDTOS = flowerService.findAllByName(name);
        return ResponseEntity.ok(flowerDTOS);
    }

    @GetMapping("/find-cost")
    public ResponseEntity<List<FlowerDTO>> findAllByCost(@RequestParam
        @DecimalMin(message = "Стоимость цветка не должна быть меньше 50 рублей", value = "50")
        @DecimalMax(message = "Стоимость цветка не должна быть больше 250 рублей", value = "250") BigDecimal cost) {
        List<FlowerDTO> flowerDTOS = flowerService.findAllByCost(cost);
        return ResponseEntity.ok(flowerDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<FlowerDTO> createFlower(@Valid @RequestBody FlowerDTO flowerDTO) {
        FlowerDTO flowerDTOCreate = flowerService.insertFlower(flowerDTO);
        return ResponseEntity.ok(flowerDTOCreate);
    }

    @PutMapping("/update")
    public ResponseEntity<FlowerDTO> updateFlower(@Valid @RequestBody FlowerDTO flowerDTO){
        FlowerDTO flowerDTOUpdate = flowerService.updateFlower(flowerDTO);
        return ResponseEntity.ok(flowerDTOUpdate);
    }
}
