package com.app.moda.americana.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContact;

	@NotNull
	@Column(nullable = false, length = 100)
	private String topic;

	@NotNull
	@Column(nullable = false, length = 350)
	private String description;

	@NotNull
	@Column(nullable = false, length = 100)
	private String fullName;
	
	@Email
	@NotNull
	@Column(nullable = false, length = 100)
	private String emailContact;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private String phoneContact;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	/* Vamos a modicar el campo createAt para que acepte cualquier tipo de fecha y no de error.
	De está forma acepta la fecha con "-" y con "/" */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateContact;

	/*
	 * Creamos un método persist - Antes de que ser guarde en la BD. Se va a
	 * ejecutar como un evento (PrePersist) de forma automatica se va a llamar a
	 * este método antes de insertar el registro en la BD. (Se inserta automaticamente la fecha en la cual se registra el usuario)
	 */
	
	@PrePersist
	public void prePersist() {
		dateContact = new Date();
	}

	public Long getIdContact() {
		return idContact;
	}

	public void setIdContact(Long idContact) {
		this.idContact = idContact;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
	}

	public String getPhoneContact() {
		return phoneContact;
	}

	public void setPhoneContact(String phoneContact) {
		this.phoneContact = phoneContact;
	}

	public Date getDateContact() {
		return dateContact;
	}

	public void setDateContact(Date dateContact) {
		this.dateContact = dateContact;
	}
}
