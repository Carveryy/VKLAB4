package org.example.vklab4.controllers;

import org.example.vklab4.dto.DrinkDto;
import org.example.vklab4.service.DrinkService;
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
    public String showDrinks(Model model) {
        model.addAttribute("drinks", service.getAllDrinks());
        model.addAttribute("newDrink", new DrinkDto());
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

    @GetMapping("/delete/{id}")
    public String deleteDrink(@PathVariable("id") Long id) {
        service.deleteDrink(id);
        return "redirect:/drinks";
    }
}
