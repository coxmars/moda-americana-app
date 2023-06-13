package com.app.moda.americana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.moda.americana.domain.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	
	User findFirstByEmail(String email);

	User findFirstByVerificationCode(String code);

	User findByResetPasswordToken(String token);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByUsername(@Param("email") String email);

}