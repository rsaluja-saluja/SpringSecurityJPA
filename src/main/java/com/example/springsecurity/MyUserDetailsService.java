package com.example.springsecurity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurity.model.MyUserDetails;
import com.example.springsecurity.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//return new MyUserDetails(userName);
		
		Optional<User> user = userRepository.findByUsername(userName);
		user.orElseThrow(()->new UsernameNotFoundException("Not Found "+userName));
		return new MyUserDetails(user.get());
	}
}
