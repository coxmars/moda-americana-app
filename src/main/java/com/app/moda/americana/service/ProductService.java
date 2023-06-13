package com.app.moda.americana.service;

import java.util.List;
import java.util.Map;

import com.app.moda.americana.domain.Products;

public interface ProductService {
    
    public List<Products> getProducts(boolean bale);
    
    public void save (Products products);
    
    public void delete (Products products);
    
    public Products getProduct(Products products);
    
    public List<Object[]> sumProductsByCategory();
    
    public Map<String, Object> getProductsByCategory();
    
    public List<Object[]> countBoughtBalesByProvider();
    
    public Map<String, Object> getBoughtBalesByProvider();
    
}