package ru.neoflex.flowershop.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import ru.neoflex.flowershop.entity.Bouquet;
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
    public ResponseEntity<List<Bouquet>> findAll() {
        return ResponseEntity.ok().body(bouquetService.findAll());
    }

    @GetMapping("/find-name")
    public ResponseEntity<List<Bouquet>> findAllByName(@RequestParam
        @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
        @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
        String name) {
        return ResponseEntity.ok().body(bouquetService.findAllByName(name));
    }

    @GetMapping("/find-cost")
    public ResponseEntity<List<Bouquet>> findAllByCost(@RequestParam
         @DecimalMin(message = "Стоимость букета не должна быть меньше 100 рублей", value = "100") BigDecimal cost){
        return ResponseEntity.ok().body(bouquetService.findAllByCost(cost));
    }

    @GetMapping("/find-flower-id")
    public ResponseEntity<List<Bouquet>> findAllByFlowerId(@RequestParam Long id){
        return ResponseEntity.ok().body(bouquetService.findAllByFlowerId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Bouquet> createBouquet(@Valid @RequestBody BouquetDTO bouquetDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bouquetService.insertBouquet(toEntity(bouquetDTO)));
    }

    @PutMapping("/update-name")
    public ResponseEntity<Bouquet> updateBouquetName(@RequestParam Long id, @RequestParam
    @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
    @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
    String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bouquetService.updateBouquetName(id, name));
    }

    @PutMapping("/update-cost")
    public ResponseEntity<Bouquet> updateBouquetCost(@RequestParam Long id, @RequestParam
    @DecimalMin(message = "Стоимость букета не должна быть меньше 100 рублей", value = "100") BigDecimal cost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bouquetService.updateBouquetCost(id, cost));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBouquet(@Valid @RequestBody BouquetDTO bouquetDTO) {
        bouquetService.deleteBouquet(toEntity(bouquetDTO));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Bouquet toEntity(BouquetDTO bouquetDTO){
        Bouquet bouquet = new Bouquet();
        bouquet.setId(bouquetDTO.getId());
        bouquet.setName(bouquetDTO.getName());
        bouquet.setNumberOfFlowers(bouquetDTO.getNumberOfFlowers());
        bouquet.setCost(bouquetDTO.getCost());
        bouquet.setFlower(bouquetDTO.getFlower());
        return bouquet;
    }
}
