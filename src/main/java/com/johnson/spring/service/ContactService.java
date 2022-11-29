package com.johnson.spring.service;

import com.johnson.spring.model.AppConstants;
import com.johnson.spring.repository.ContactRepository;
import com.johnson.spring.model.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.Collection;
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
        int saved = contactRepository.saveContact(contact);
        return saved > 0;
    }

    public List<Contact> findMessagesWithOpenStatus() {
       return contactRepository.findMessagesWithOpenStatus(AppConstants.OPEN);
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        int saved = contactRepository.updateMsgStatus(contactId, updatedBy, AppConstants.CLOSE);
        return saved > 0;
    }
}
