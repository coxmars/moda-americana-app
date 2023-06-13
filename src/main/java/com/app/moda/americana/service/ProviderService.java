package com.app.moda.americana.service;

import java.util.List;

import com.app.moda.americana.domain.Contact;
import com.app.moda.americana.domain.Provider;

public interface ProviderService {
    
    public List<Provider> getProviders();
    
    public void save (Provider provider);
    
    public void delete (Provider provider);
    
    public Provider getProvider(Provider provider);
    
}