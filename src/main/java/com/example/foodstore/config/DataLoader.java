package com.example.foodstore.config;

import com.example.foodstore.model.Role;
import com.example.foodstore.model.User;
import com.example.foodstore.repository.RoleRepository;
import com.example.foodstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private final  UserRepository userRepository;
    private final  PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //find existing role
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Role roleUser = roleRepository.findByName("ROLE_USER");
        //create new role if not exist
        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleRepository.save(roleAdmin);
        }
        if (roleUser == null) {
            roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleRepository.save(roleUser);
        }
        //create new user if not exist
        if (userRepository.findByUsername("admin").isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleAdmin);
            User admin = new User(1L, "admin", passwordEncoder.encode("admin"), roles);
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleUser);
            User user = new User(2L, "user", passwordEncoder.encode("user"), roles);
            userRepository.save(user);
        }
    }
}
