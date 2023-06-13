package com.app.moda.americana.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.moda.americana.domain.Provider;

public interface IProviderRepository extends CrudRepository <Provider, Long> {
	
}
