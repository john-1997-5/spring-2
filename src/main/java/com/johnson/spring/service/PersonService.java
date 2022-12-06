package com.johnson.spring.service;

import com.johnson.spring.model.AppConstants;
import com.johnson.spring.model.entities.Person;
import com.johnson.spring.model.entities.Role;
import com.johnson.spring.repository.PersonRepository;
import com.johnson.spring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean createNewPerson(Person person) {
        Role role = roleRepository.getByRoleName(AppConstants.STUDENT_ROLE);
        person.setRole(role);
        Person save = personRepository.save(person);
        return save != null;
    }
}
