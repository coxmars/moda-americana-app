package com.app.moda.americana.domain;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "products")
public class Products implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id")
    private Long productId;

    @NotNull
    @Column(name = "Product_Name", nullable = false, length = 100)
    private String productName;

    private boolean isBale;

    @NotNull
    @Column(name = "Product_Description", nullable = false, length = 150)
    private String productDescription;

    // Relaciones con las tablas categoria/proveedor // 
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;
    
    // -------------------------------------------- //

    @NotNull
    @Column(name = "productQuantity", nullable = false)
    private int productQuantity;

    @NotNull
    @Column(name = "Unit_Price", nullable = false)
    private Float unitPrice;

    @NotNull
    @Column(name = "Gender_Product", nullable = false, length = 15)
    private String genderProduct;

    @Column(name = "Photo_Product", length = 50)
    private String photoProduct;

    @NotNull
    @Column(name = "Product_Price", nullable = false)
    private Float productPrice;

    @NotNull
    @Column(name = "Date_Product")
    @Temporal(TemporalType.DATE)
	/* Vamos a modicar el campo createAt para que acepte cualquier tipo de fecha y no de error.
	De está forma acepta la fecha con "-" y con "/" */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateProduct;

    /*
     * Creamos un método persist - Antes de que ser guarde en la BD. Se va a
     * ejecutar como un evento (PrePersist) de forma automatica se va a llamar a
     * este método antes de insertar el registro en la BD. (Se inserta automaticamente la fecha en la cual se registra el usuario)
     */

    @PrePersist
    public void prePersist() {
        dateProduct = new Date();
    }
    
    @PreUpdate
    public void preUpdate() {
    	dateProduct = new Date();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isBale() {
        return isBale;
    }

    public void setIsBale(boolean bale) {
        isBale = bale;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getGenderProduct() {
        return genderProduct;
    }

    public void setGenderProduct(String genderProduct) {
        this.genderProduct = genderProduct;
    }

    public String getPhotoProduct() {
        return photoProduct;
    }

    public void setPhotoProduct(String photoProduct) {
        this.photoProduct = photoProduct;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Date getDateProduct() {
        return dateProduct;
    }

    public void setDateProduct(Date dateProduct) {
        this.dateProduct = dateProduct;
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
    
}
