package com.capston.project.merchantmanagement;

import com.capston.project.merchantmanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MerchantManagementApplication {

	@Autowired
	private UserRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(MerchantManagementApplication.class, args);
	}

	@Bean
	UserDetailsService userDetailsService(){
		return username -> repo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

}
