package com.app.moda.americana.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Category_Id ")
    private Long categoryId;

    @NotNull
    @Column(name = "Category_Name", nullable = false, length = 50)
    private String categoryName;

    @NotNull
    @Column(name = "Category_Description", nullable = false, length = 500)
    private String categoryDescription;
    
    @OneToMany(mappedBy = "category")
    private List<Products> products;

    @NotNull
    @Column(name = "Date_Category")
    @Temporal(TemporalType.DATE)
	/* Vamos a modicar el campo createAt para que acepte cualquier tipo de fecha y no de error.
	De está forma acepta la fecha con "-" y con "/" */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateCategory;

    /*
     * Creamos un método persist - Antes de que ser guarde en la BD. Se va a
     * ejecutar como un evento (PrePersist) de forma automatica se va a llamar a
     * este método antes de insertar el registro en la BD. (Se inserta automaticamente la fecha en la cual se registra el usuario)
     */

    @PrePersist
    public void prePersist() {
        dateCategory = new Date();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Date getDateCategory() {
        return dateCategory;
    }

    public void setDateCategory(Date dateCategory) {
        this.dateCategory = dateCategory;
    }

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}
    
}
