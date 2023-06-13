package com.app.moda.americana.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "provider")
public class Provider implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Provider_Id")
    private Long providerId;

    @NotNull
    @Column(name = "Provider_Name", nullable = false, length = 50)
    private String providerName;

    /*
    @NotNull
    @Column(name = "Provider_Description", nullable = false, length = 300)
    private String providerDescription;
    */
    
    @OneToMany(mappedBy = "provider")
    private List<Products> products;
    
    @Email
	@NotNull
	@Column(nullable = false, length = 45)
	private String providerEmail;
    
	@NotNull
	@Column(nullable = false, length = 8)
	private String providerPhoneNumber;

    @NotNull
    @Column(name = "Date_Provider")
    @Temporal(TemporalType.DATE)
	/* Vamos a modicar el campo createAt para que acepte cualquier tipo de fecha y no de error.
	De está forma acepta la fecha con "-" y con "/" */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateProvider;

    /*
     * Creamos un método persist - Antes de que ser guarde en la BD. Se va a
     * ejecutar como un evento (PrePersist) de forma automatica se va a llamar a
     * este método antes de insertar el registro en la BD. (Se inserta automaticamente la fecha en la cual se registra el usuario)
     */

    @PrePersist
    public void prePersist() {
    	dateProvider = new Date();
    }
    
    @PreUpdate
    public void preUpdate() {
        dateProvider = new Date();
    }

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	/*
	public String getProviderDescription() {
		return providerDescription;
	}

	public void setProviderDescription(String providerDescription) {
		this.providerDescription = providerDescription;
	}
	*/

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getProviderPhoneNumber() {
		return providerPhoneNumber;
	}

	public void setProviderPhoneNumber(String providerPhoneNumber) {
		this.providerPhoneNumber = providerPhoneNumber;
	}

	public Date getDateProvider() {
		return dateProvider;
	}

	public void setDateProvider(Date dateProvider) {
		this.dateProvider = dateProvider;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

    
}
