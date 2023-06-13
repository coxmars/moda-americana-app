package com.app.moda.americana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.moda.americana.domain.Provider;
import com.app.moda.americana.repository.IProviderRepository;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private IProviderRepository providerRepository;

	@Override
	@Transactional(readOnly = true) // Este metodo hace una conexión de solo lectura
	public List<Provider> getProviders() {
		return (List<Provider>) providerRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Provider provider) {
		if (provider == null) {
			throw new IllegalArgumentException("El objeto Provider no puede ser nulo.");
		}
		if (provider.getProviderName() == null || provider.getProviderName().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Name es obligatorio.");
		}
		if (provider.getProviderEmail() == null || provider.getProviderEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Email es obligatorio.");
		}
		if (provider.getProviderPhoneNumber() == null || provider.getProviderPhoneNumber().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Phone Number es obligatorio.");
		}
		providerRepository.save(provider);
	}

	@Override
	@Transactional
	public void delete(Provider provider) {
		providerRepository.delete(provider);
	}

	@Override
	@Transactional(readOnly = true) // Este metodo hace una conexión de solo lectura
	public Provider getProvider(Provider provider) {
		// Se hace busqueda por ID, en caso contrario es null
		return providerRepository.findById(provider.getProviderId()).orElse(null);
	}

}
