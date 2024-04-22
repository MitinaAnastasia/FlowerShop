package ru.neoflex.flowershop.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.neoflex.flowershop.entity.Additive;
import ru.neoflex.flowershop.entity.Provider;
import ru.neoflex.flowershop.entity.Season;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FlowerDTO {

    private Long id;

    @NotBlank(message = "Название должно содержать хотя бы один непробельный символ")
    @Pattern(regexp = "^[а-яА-ЯЁё ]+$", message = "Название должно содержать только буквы русского алфавита и пробелы")
    private String name;

    @DecimalMin(message = "Стоимость цветка не должна быть меньше 50 рублей", value = "50")
    @DecimalMax(message = "Стоимость цветка не должна быть больше 250 рублей", value = "250")
    private BigDecimal cost;

    @Positive(message = "Высота стебля цветка должна быть положительным числом")
    private Integer height;

    private String description;

    @Min(message = "Период нахождения цветка в воде не должен быть меньше 7 дней", value = 7)
    @Max(message = "Период нахождения цветка в воде не должен быть больше 31 дня", value = 31)
    private Integer periodInWater;

    @NotNull
    private Season season;

    @NotNull
    private Provider provider;

    private Set<Additive> additives = new HashSet<>();

}
