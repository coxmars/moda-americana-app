package com.app.moda.americana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.moda.americana.domain.Contact;
import com.app.moda.americana.repository.IContactRepository;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private IContactRepository contactRepository;
    
	@Override
	@Transactional(readOnly = true) // Este metodo hace una conexión de solo lectura
	public List<Contact> getContacts() {
		return (List<Contact>) contactRepository.findAll();
	}

	@Override
	public void save(Contact contact) {
		contactRepository.save(contact);
	}

	@Override
	public void delete(Contact contact) {
		contactRepository.delete(contact);
	}

	@Override
	@Transactional(readOnly = true) // Este metodo hace una conexión de solo lectura
	public Contact getContact(Contact contact) {
		// Se hace busqueda por ID, en caso contrario es null
        return contactRepository.findById(contact.getIdContact()).orElse(null);
	}
    
}
