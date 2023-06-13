package com.app.moda.americana.service;

import com.app.moda.americana.config.CustomUserDetailsImpl;
import com.app.moda.americana.domain.User;
import com.app.moda.americana.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final IUserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.getUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    
    return new CustomUserDetailsImpl(user);
  }
}