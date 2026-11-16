package vklab4.controllers;

import vklab4.dto.DrinkDto;
import vklab4.service.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drinks")
public class DrinkController {
    private final DrinkService service;

    public DrinkController(DrinkService service) {
        this.service = service;
    }

    @GetMapping
    public String showDrinks(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String type,
                             @RequestParam(required = false) Double minVolume,
                             @RequestParam(required = false) Double maxVolume,
                             @RequestParam(required = false) Double minPrice,
                             @RequestParam(required = false) Double maxPrice,
                             Model model) {
        model.addAttribute("drinks",
                service.searchDrinks(name, type, minVolume, maxVolume, minPrice, maxPrice));
        model.addAttribute("newDrink", new DrinkDto());
        model.addAttribute("filterName", name);
        model.addAttribute("filterType", type);
        model.addAttribute("filterMinVolume", minVolume);
        model.addAttribute("filterMaxVolume", maxVolume);
        model.addAttribute("filterMinPrice", minPrice);
        model.addAttribute("filterMaxPrice", maxPrice);
        return "drinks-page";
    }

    @PostMapping("/add")
    public String addDrink(@ModelAttribute("newDrink") DrinkDto drinkDto) {
        service.saveDrink(drinkDto);
        return "redirect:/drinks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("drinkToEdit", service.getDrinkById(id));
        return "edit-drink";
    }

    @PostMapping("/update/{id}")
    public String updateDrink(@PathVariable("id") Long id, @ModelAttribute("drinkToEdit") DrinkDto drinkDto) {
        drinkDto.setId(id);
        service.saveDrink(drinkDto);
        return "redirect:/drinks";
    }

    @PostMapping("/delete/{id}")
    public String deleteDrink(@PathVariable("id") Long id) {
        service.deleteDrink(id);
        return "redirect:/drinks";
    }
}