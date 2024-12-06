//package com.fantasy.packaging.backend.config;
//
//import com.fantasy.packaging.backend.model.User;
//import com.fantasy.packaging.backend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class DataInitializer implements CommandLineRunner {
//
//  private final UserRepository userRepository;
//  private final PasswordEncoder passwordEncoder;
//
//  @Autowired
//  public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//    this.userRepository = userRepository;
//    this.passwordEncoder = passwordEncoder;
//  }
//
//  @Override
//  public void run(String... args) {
//    try {
//      String testEmail = "admin@example.com";
//      String testPassword = "admin123";
//
//      if (!userRepository.findByEmail(testEmail).isPresent()) {
//        User user = new User();
//        user.setEmail(testEmail);
//        user.setPassword(passwordEncoder.encode(testPassword));
//        user.setEnabled(true);
//        userRepository.save(user);
//        log.info("Test user created successfully!");
//      } else {
//        log.info("Test user already exists");
//      }
//
//      // Verify the user can be retrieved
//      userRepository.findByEmail(testEmail).ifPresent(user -> {
//        log.info("Test user verification - Email: {}, Enabled: {}",
//            user.getEmail(), user.isEnabled());
//      });
//
//    } catch (Exception e) {
//      log.error("Error initializing test data", e);
//    }
//  }
//}