package com.johnson.spring.controller;

import com.johnson.spring.model.entities.Person;
import com.johnson.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Autowired
    PersonService personService;

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
        Person loggedInPerson = personService.getPersonByEmail(authentication.getName());
        model.addAttribute("username", loggedInPerson.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        httpSession.setAttribute("loggedInPerson", loggedInPerson);
        return "dashboard";
    }
}
