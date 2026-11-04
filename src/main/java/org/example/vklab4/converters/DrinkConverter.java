package org.example.vklab4.converters;

import org.example.vklab4.Data.Drink;
import org.example.vklab4.dto.DrinkDto;
import org.springframework.stereotype.Component;

@Component
public class DrinkConverter {

    public DrinkDto toDto(Drink drink) {
        DrinkDto dto = new DrinkDto();
        dto.setId(drink.getId());
        dto.setName(drink.getName());
        dto.setType(drink.getType());
        dto.setVolume(drink.getVolume());
        dto.setPrice(drink.getPrice());
        return dto;
    }

    public Drink toEntity(DrinkDto dto) {
        Drink drink = new Drink();
        drink.setId(dto.getId());
        drink.setName(dto.getName());
        drink.setType(dto.getType());
        drink.setVolume(dto.getVolume());
        drink.setPrice(dto.getPrice());
        return drink;
    }
}