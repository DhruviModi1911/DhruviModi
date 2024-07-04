package com.example.user_role;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.user_role.entity.Role;
import com.example.user_role.entity.RoleName;
import com.example.user_role.entity.User;
import com.example.user_role.repo.RoleRepository;
import com.example.user_role.repo.UserRepository;

@SpringBootApplication
public class UserRoleApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserRoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       
        Role userRole = roleRepository.findByRoleName(RoleName.USER)
                .orElseGet(() -> roleRepository.save(new Role(RoleName.USER)));

     
        Role adminRole = roleRepository.findByRoleName(RoleName.ADMIN)
                .orElseGet(() -> roleRepository.save(new Role(RoleName.ADMIN)));

       
        User admin = userRepository.findByUsername("admin")
                .orElseGet(() -> {
                    User newAdmin = new User();
                    newAdmin.setUsername("admin");
                    newAdmin.setPassword(new BCryptPasswordEncoder().encode("admin_password"));
                    newAdmin.setRoles(Set.of(adminRole));
                    return userRepository.save(newAdmin);
                });

       
        User defaultUser = userRepository.findByUsername("user")
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername("user");
                    newUser.setPassword(new BCryptPasswordEncoder().encode("user_password"));
                    newUser.setRoles(Set.of(userRole));
                    return userRepository.save(newUser);
                });
    }
}
