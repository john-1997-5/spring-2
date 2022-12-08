package com.johnson.spring.controller;

import com.johnson.spring.model.Profile;
import com.johnson.spring.model.entities.Person;
import com.johnson.spring.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
public class ProfileController {
    @Autowired
    PersonService personService;

    @GetMapping("displayProfile")
    public String displayMessage(Model model, HttpSession httpSession) {
        Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
        Profile profile = new Profile();
        profile.setName(loggedInPerson.getName());
        profile.setEmail(loggedInPerson.getEmail());
        profile.setMobileNumber(loggedInPerson.getMobileNumber());
        if (loggedInPerson.getAddress() != null) {
            profile.setAddress1(loggedInPerson.getAddress().getAddress1());
            profile.setAddress2(loggedInPerson.getAddress().getAddress2());
            profile.setCity(loggedInPerson.getAddress().getCity());
            profile.setState(loggedInPerson.getAddress().getState());
            profile.setZipCode(String.valueOf(loggedInPerson.getAddress().getZipCode()));
        }
        model.addAttribute("profile", profile);
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession httpSession) {
        if (errors.hasErrors()) {
            log.error("error while updating profile info...");
            return "profile";

        }
        boolean updated = personService.updatePersonInfo(profile, (Person) httpSession.getAttribute("loggedInPerson"));
        if (updated) {
            log.info("user updated okay");
        }
        return "redirect:/displayProfile";
    }
}
