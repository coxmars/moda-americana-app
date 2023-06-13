package com.app.moda.americana.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.moda.americana.domain.Contact;

public interface IContactRepository extends CrudRepository <Contact, Long> {
	
}
