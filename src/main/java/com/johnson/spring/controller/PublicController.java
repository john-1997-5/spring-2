package com.johnson.spring.controller;

import com.johnson.spring.model.entities.Person;
import com.johnson.spring.repository.PersonRepository;
import com.johnson.spring.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {
    @Autowired
    PersonService personService;

    @GetMapping("/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if (errors.hasErrors()) {
            log.error("error while creating new user");
            return "register.html";
        }
        if (personService.createNewPerson(person)) {
            log.info("new user created succesfully my boy!");
            return "redirect:/login?register=true";
        }
        return "redirect:/login?register=false";
    }

}