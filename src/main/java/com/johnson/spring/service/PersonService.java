package com.johnson.spring.service;

import com.johnson.spring.model.AppConstants;
import com.johnson.spring.model.Profile;
import com.johnson.spring.model.entities.Address;
import com.johnson.spring.model.entities.Person;
import com.johnson.spring.model.entities.Role;
import com.johnson.spring.repository.PersonRepository;
import com.johnson.spring.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean createNewPerson(Person person) {
        Role role = roleRepository.getByRoleName(AppConstants.STUDENT_ROLE);
        person.setRole(role);
        //encode password
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        Person save = personRepository.save(person);
        return save != null;
    }

    public Person getPersonByEmail(String email) {
        return personRepository.readByEmail(email);
    }

    public boolean updatePersonInfo(Profile profile, Person person) {

        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());

        if (person.getAddress() == null) {
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        if (profile.getAddress2() != null) {
            person.getAddress().setAddress2(profile.getAddress2());
        }
        Person save = personRepository.save(person);

        return save != null;
    }
}
