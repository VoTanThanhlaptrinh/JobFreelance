package com.job_freelance_internal_db.service.impl;

import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.object.Role;
import com.job_freelance_internal_db.object.User;
import com.job_freelance_internal_db.repositories.RoleRepository;
import com.job_freelance_internal_db.repositories.UserRepository;
import com.job_freelance_internal_db.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Response checkUser(String username, String password) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isEmpty()) {
            return new Response(400,null, "User not found");
        }
        if(!passwordEncoder.matches(password, user.get().getPassword())) {
            return new Response(400,null, "Wrong password");
        }
        return new Response(200,user.get(), "Successfully logged in");
    }

    @Override
    @Transactional
    public Response register(User user) {
        try {
            Role role = roleRepository.findByName("ROLE_USER").get();
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.saveAndFlush(user);
            return new Response(200,null, "Registration successful");
        }catch (DataIntegrityViolationException e){
            return new Response(400,null, e.getMessage());
        }
    }
}
