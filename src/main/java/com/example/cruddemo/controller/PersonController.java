package com.example.cruddemo.controller;

import com.example.cruddemo.model.Person;
import com.example.cruddemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "person-list";
    }

    @GetMapping("/new")
    public String showPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute Person person) {
        personService.createPerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        model.addAttribute("person", person);
        return "person-form";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "redirect:/persons";
    }
}