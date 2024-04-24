package ru.neoflex.flowershop.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ErrorConstant {
    public static final String FLOWERS_NOT_FOUND_MESSAGE = "Flowers not found";
    public static final String FLOWERS_WITH_NAME_NOT_FOUND_MESSAGE = "Not found Flowers with name: ";
    public static final String FLOWERS_WITH_COST_NOT_FOUND_MESSAGE = "Not found Flowers with cost: ";
    public static final String FLOWER_WITH_ID_NOT_FOUND_MESSAGE = "Not found Flower with id: ";
    public static final String BOUQUETS_NOT_FOUND_MESSAGE = "Bouquets not found";
    public static final String BOUQUETS_WITH_NAME_NOT_FOUND_MESSAGE = "Not found Bouquets with name: ";
    public static final String BOUQUETS_WITH_COST_NOT_FOUND_MESSAGE = "Not found Bouquets with cost: ";
    public static final String BOUQUETS_WiTH_FLOWERS_WITH_ID_NOT_FOUND_MESSAGE = "Not found Bouquets with flowers with id: ";
    public static final String BOUQUET_WITH_ID_NOT_FOUND_MESSAGE = "Not found Bouquet with id: ";

}
