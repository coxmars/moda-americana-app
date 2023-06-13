package com.app.moda.americana.service;

import java.util.List;

import com.app.moda.americana.domain.Contact;

public interface ContactService {
    
    public List<Contact> getContacts();
    
    public void save (Contact contact);
    
    public void delete (Contact contact);
    
    public Contact getContact(Contact contact);
    
}