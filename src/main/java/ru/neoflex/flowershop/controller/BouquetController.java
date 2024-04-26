package ru.neoflex.flowershop.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/bouquet")
public class BouquetController {

    private final BouquetService bouquetService;

    @GetMapping("/all")
    public ResponseEntity<List<BouquetDTO>> findAll() {
        List<BouquetDTO> bouquetDTOS = bouquetService.findAll();
        return ResponseEntity.ok(bouquetDTOS);
    }

    @GetMapping("/find-name")
    public ResponseEntity<List<BouquetDTO>> findAllByName(@RequestParam
        @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
        @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
        String name) {
        List<BouquetDTO> bouquetDTOS = bouquetService.findAllByName(name);
        return ResponseEntity.ok(bouquetDTOS);
    }

    @GetMapping("/find-cost")
    public ResponseEntity<List<BouquetDTO>> findAllByCost(@RequestParam
         @DecimalMin(message = "Стоимость букета не должна быть меньше 100 рублей", value = "100") BigDecimal cost){
        List<BouquetDTO> bouquetDTOS = bouquetService.findAllByCost(cost);
        return ResponseEntity.ok(bouquetDTOS);
    }

    @GetMapping("/find-flower-id")
    public ResponseEntity<List<BouquetDTO>> findAllByFlowerId(@RequestParam Long id){
        List<BouquetDTO> bouquetDTOS = bouquetService.findAllByFlowerId(id);
        return ResponseEntity.ok(bouquetDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<BouquetDTO> createBouquet(@Valid @RequestBody BouquetDTO bouquetDTO){
        BouquetDTO bouquetDTOCreate = bouquetService.insertBouquet(bouquetDTO);
        return ResponseEntity.ok(bouquetDTOCreate);
    }

    @PutMapping("/update")
    public ResponseEntity<BouquetDTO> updateBouquet(@Valid @RequestBody BouquetDTO bouquetDTO) {
        BouquetDTO bouquetDTOUpdate = bouquetService.updateBouquet(bouquetDTO);
        return ResponseEntity.ok(bouquetDTOUpdate);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBouquet(@RequestParam Long id) {
        bouquetService.deleteBouquet(id);
        return ResponseEntity.ok().build();
    }
}
