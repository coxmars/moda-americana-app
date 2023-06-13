package com.app.moda.americana.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@NotNull
	@Column(nullable = false, length = 64)
	private String password;

	// Un usuario puede tener muchos roles, se puede hacer por grupos/privilegios pero este es simple
	
	@OneToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="user_id")
	private List<Role> roles;

	@NotNull
	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@NotNull
	@Column(name = "create_At")
	@Temporal(TemporalType.DATE)
	/* Vamos a modicar el campo createAt para que acepte cualquier tipo de fecha y no de error.
	De está forma acepta la fecha con "-" y con "/" */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;

	/*
	 * Creamos un método persist - Antes de que ser guarde en la BD. Se va a
	 * ejecutar como un evento (PrePersist) de forma automatica se va a llamar a
	 * este método antes de insertar el registro en la BD. (Se inserta automaticamente la fecha en la cual se registra el usuario)
	 */
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	@NotNull
	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Column(name = "verification_code", length = 64)
	private String verificationCode;

	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	@Column(name = "failed_attempt")
	private int failedAttempt;

	@Column(name = "lock_time")
	private Date lockTime;

	@Column(name = "identification", length = 20)
	private String identification;

	@Column(name = "identification_type", length = 20)
	private String identificationType;

	@Column(name = "gender", length = 15)
	private String gender;

	@Min(value = 0)
	@Column(name = "total_bought")
	private Float totalBought;

	@Column(name = "phone_number", length = 20)
	private String phoneNumber;

	private boolean promotion = false;
	
	private boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Float getTotalBought() {
		return totalBought;
	}

	public void setTotalBought(Float totalBought) {
		this.totalBought = totalBought;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		User user = (User) o;
		return id != null && Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", firstName='"
				+ firstName + '\'' + ", lastName='" + lastName + '\'' + ", verificationCode='" + verificationCode + '\''
				+ ", resetPasswordToken='" + resetPasswordToken + '\'' + ", accountNonLocked=" + accountNonLocked
				+ ", failedAttempt=" + failedAttempt + ", lockTime=" + lockTime + ", enabled=" + enabled + '}';
	}
}