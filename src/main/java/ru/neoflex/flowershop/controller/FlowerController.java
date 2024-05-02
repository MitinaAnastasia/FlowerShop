package ru.neoflex.flowershop.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/flower")
public class FlowerController {

    private final FlowerService flowerService;

    @GetMapping("/all")
    public ResponseEntity<List<FlowerDTO>> findAll() {
        log.debug("FlowerController.findAll - start of work");
        List<FlowerDTO> flowerDTOS = flowerService.findAll();
        log.debug("FlowerController.findAll - all flowerDTOS {}", flowerDTOS);
        return ResponseEntity.ok(flowerDTOS);
    }

    @GetMapping("/find-name")
    public ResponseEntity<List<FlowerDTO>> findAllByName(@RequestParam
        @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
        @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
        String name) {
        log.debug("FlowerController.findAllByName - input data: name {}", name);
        List<FlowerDTO> flowerDTOS = flowerService.findAllByName(name);
        log.debug("FlowerController.findAllByName - all flowerDTOS by name {}", flowerDTOS);
        return ResponseEntity.ok(flowerDTOS);
    }

    @GetMapping("/find-cost")
    public ResponseEntity<List<FlowerDTO>> findAllByCost(@RequestParam
        @DecimalMin(message = "Стоимость цветка не должна быть меньше 50 рублей", value = "50")
        @DecimalMax(message = "Стоимость цветка не должна быть больше 250 рублей", value = "250") BigDecimal cost) {
        log.debug("FlowerController.findAllByCost - input data: cost {}", cost);
        List<FlowerDTO> flowerDTOS = flowerService.findAllByCost(cost);
        log.debug("FlowerController.findAllByCost - all flowerDTOS by cost {}", flowerDTOS);
        return ResponseEntity.ok(flowerDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<FlowerDTO> createFlower(@Valid @RequestBody FlowerDTO flowerDTO) {
        log.debug("FlowerController.createFlower - input data: flowerDTO {}", flowerDTO);
        FlowerDTO flowerDTOCreate = flowerService.insertFlower(flowerDTO);
        log.debug("FlowerController.createFlower - flowerDTOCreate {}", flowerDTOCreate);
        return ResponseEntity.ok(flowerDTOCreate);
    }

    @PutMapping("/update")
    public ResponseEntity<FlowerDTO> updateFlower(@Valid @RequestBody FlowerDTO flowerDTO){
        log.debug("FlowerController.updateFlower - input data: flowerDTO {}", flowerDTO);
        FlowerDTO flowerDTOUpdate = flowerService.updateFlower(flowerDTO);
        log.debug("FlowerController.updateFlower - flowerDTOUpdate {}", flowerDTOUpdate);
        return ResponseEntity.ok(flowerDTOUpdate);
    }
}
