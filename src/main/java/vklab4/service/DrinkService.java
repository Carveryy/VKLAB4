package vklab4.service;

import vklab4.converters.DrinkConverter;
import vklab4.Data.Drink;
import vklab4.dto.DrinkDto;
import vklab4.repository.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {
    private final DrinkRepository repository;
    private final DrinkConverter converter;

    public DrinkService(DrinkRepository repository, DrinkConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<DrinkDto> getAllDrinks() {
        return repository.findAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public List<DrinkDto> searchDrinks(String name, String type,
                                       Double minVolume, Double maxVolume,
                                       Double minPrice, Double maxPrice) {
        String n = name == null ? "" : name.trim();
        String t = type == null ? "" : type.trim();
        return repository.search(n, t, minVolume, maxVolume, minPrice, maxPrice).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public DrinkDto getDrinkById(Long id) {
        Drink drink = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid drink Id:" + id));
        return converter.toDto(drink);
    }

    public void saveDrink(DrinkDto drinkDto) {
        Drink drink = converter.toEntity(drinkDto);
        repository.save(drink);
    }

    public void deleteDrink(Long id) {
        repository.deleteById(id);
    }
}