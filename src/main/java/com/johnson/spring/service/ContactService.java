package com.johnson.spring.service;

import com.johnson.spring.model.AppConstants;
import com.johnson.spring.repository.ContactRepository;
import com.johnson.spring.model.entities.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScope
//@SessionScope
//@RequestScope
@Service
@Slf4j
@Getter
@Setter
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public boolean saveMessage(Contact contact) {
        contact.setStatus(AppConstants.OPEN);
        contact.setCreatedBy(AppConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        return savedContact != null;
    }

    public List<Contact> findMessagesWithOpenStatus() {
        /*List<Contact> contactMessages = new ArrayList<>();
        Iterable<Contact> all = contactRepository.findAll();
        all.forEach(contactMessages::add);
        return contactMessages.stream()
                .filter(contact -> contact.getStatus().equals(AppConstants.OPEN))
                .collect(Collectors.toList());*/
        return contactRepository.findByStatus(AppConstants.OPEN);
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        Optional<Contact> tmpOptional = contactRepository.findById(contactId);
        tmpOptional.ifPresent(contact -> {
            contact.setStatus(AppConstants.CLOSE);
            contact.setUpdatedBy(updatedBy);
            contact.setUpdatedAt(LocalDateTime.now());
        });
        Contact updatedContact = contactRepository.save(tmpOptional.get());
        return updatedContact != null;
    }
}
