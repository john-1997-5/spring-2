package com.johnson.spring.controller;

import com.johnson.spring.model.Contact;
import com.johnson.spring.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to: {}", errors.toString());
            return "contact.html"; // mantiene la misma sesión (no refresca la página)
        }
        boolean messageSaved = contactService.saveMessage(contact);
        log.info(messageSaved ? "messaged saved successfully!" : "error while saving message");
        return "redirect:/contact"; // ejecuta dicho endpoint nuevamente (refresco)
    }

    @GetMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @GetMapping("/displayMessages")
    public String displayMessages(Model model) {
        List<Contact> contactMsgs = contactService.findMessagesWithOpenStatus();
        model.addAttribute("contactMsgs", contactMsgs);
        return "messages.html";
    }

    @GetMapping("/closeMsg")
    public String closeMessage(@RequestParam("id") int id, Authentication auth) {
        boolean isMsgClosed = contactService.updateMsgStatus(id, auth.getName());
        log.info(isMsgClosed ? "message closed successfully!" : "error while closing message");
        return "redirect:/displayMessages";
    }
}
