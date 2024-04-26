package ru.neoflex.flowershop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import ru.neoflex.flowershop.entity.Flower;

import java.math.BigDecimal;

@Getter
@Setter
public class BouquetDTO {

    private Long id;

    @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
    @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
    private String name;

    @Min(message = "Количество цветов в букете минимум 2", value = 2)
    private Integer numberOfFlowers;

    @DecimalMin(message = "Стоимость букета не должна быть меньше 100 рублей", value = "100")
    private BigDecimal cost;

    @NotNull
    private Flower flower;
}
