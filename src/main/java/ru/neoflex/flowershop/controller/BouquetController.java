package ru.neoflex.flowershop.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.neoflex.flowershop.dto.BouquetDTO;
import ru.neoflex.flowershop.service.BouquetService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
@RequestMapping("/bouquet")
public class BouquetController {

    private final BouquetService bouquetService;

    @GetMapping("/all")
    public ResponseEntity<List<BouquetDTO>> findAll() {
        log.debug("BouquetController.findAll - start of work");
        List<BouquetDTO> bouquetDTOS = bouquetService.findAll();
        log.debug("BouquetController.findAll - bouquetDTOS {}", bouquetDTOS);
        return ResponseEntity.ok(bouquetDTOS);
    }

    @GetMapping("/find-name")
    public ResponseEntity<List<BouquetDTO>> findAllByName(@RequestParam
        @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
        @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
        String name) {
        log.debug("BouquetController.findAllByName - input data: name {}", name);
        List<BouquetDTO> bouquetDTOS = bouquetService.findAllByName(name);
        log.debug("BouquetController.findAllByName - bouquetDTOS {}", bouquetDTOS);
        return ResponseEntity.ok(bouquetDTOS);
    }

    @GetMapping("/find-cost")
    public ResponseEntity<List<BouquetDTO>> findAllByCost(@RequestParam
         @DecimalMin(message = "Стоимость букета не должна быть меньше 100 рублей", value = "100") BigDecimal cost){
        log.debug("BouquetController.findAllByCost - input data: cost {}", cost);
        List<BouquetDTO> bouquetDTOS = bouquetService.findAllByCost(cost);
        log.debug("BouquetController.findAllByCost - bouquetDTOS {}", bouquetDTOS);
        return ResponseEntity.ok(bouquetDTOS);
    }

    @GetMapping("/find-flower-id")
    public ResponseEntity<List<BouquetDTO>> findAllByFlowerId(@RequestParam Long id){
        log.debug("BouquetController.findAllByFlowerId - input data: id {}", id);
        List<BouquetDTO> bouquetDTOS = bouquetService.findAllByFlowerId(id);
        log.debug("BouquetController.findAllByFlowerId - bouquetDTOS {}", bouquetDTOS);
        return ResponseEntity.ok(bouquetDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<BouquetDTO> createBouquet(@Valid @RequestBody BouquetDTO bouquetDTO){
        log.debug("BouquetController.createBouquet - input data: bouquetDTO {}", bouquetDTO);
        BouquetDTO bouquetDTOCreate = bouquetService.insertBouquet(bouquetDTO);
        log.debug("BouquetController.createBouquet - bouquetDTOCreate {}", bouquetDTOCreate);
        return ResponseEntity.ok(bouquetDTOCreate);
    }

    @PutMapping("/update")
    public ResponseEntity<BouquetDTO> updateBouquet(@Valid @RequestBody BouquetDTO bouquetDTO) {
        log.debug("BouquetController.updateBouquet - input data: bouquetDTO {}", bouquetDTO);
        BouquetDTO bouquetDTOUpdate = bouquetService.updateBouquet(bouquetDTO);
        log.debug("BouquetController.updateBouquet - bouquetDTOUpdate {}", bouquetDTOUpdate);
        return ResponseEntity.ok(bouquetDTOUpdate);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBouquet(@RequestParam Long id) {
        log.debug("BouquetController.deleteBouquet - input data: id {}", id);
        bouquetService.deleteBouquet(id);
        log.debug("BouquetController.deleteBouquet - end of work");
        return ResponseEntity.ok().build();
    }
}
