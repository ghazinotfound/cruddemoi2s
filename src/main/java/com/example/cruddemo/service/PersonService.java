package com.example.cruddemo.service;

import com.example.cruddemo.model.Person;
import com.example.cruddemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        person.setNik(personDetails.getNik());
        person.setNama(personDetails.getNama());
        person.setJenisKelamin(personDetails.getJenisKelamin());
        person.setTanggalLahir(personDetails.getTanggalLahir());
        person.setAlamat(personDetails.getAlamat());
        person.setNegara(personDetails.getNegara());
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}