package com.example.user_role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_role.entity.Role;
import com.example.user_role.entity.RoleName;
import com.example.user_role.entity.User;
import com.example.user_role.repo.RoleRepository;
import com.example.user_role.repo.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user, RoleName roleName) {
        Role role = roleRepository.findByRoleName(roleName)
                                  .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
