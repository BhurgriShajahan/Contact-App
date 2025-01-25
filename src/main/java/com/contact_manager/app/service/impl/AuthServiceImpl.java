package com.contact_manager.app.service.impl;

import com.contact_manager.app.enums.RoleTypes;
import com.contact_manager.app.model.entities.CustomResponseEntity;
import com.contact_manager.app.model.entities.Roles;
import com.contact_manager.app.model.entities.User;
import com.contact_manager.app.model.mapper.UserSignupMapper;
import com.contact_manager.app.model.request.LoginRequest;
import com.contact_manager.app.model.request.UserSignupDto;
import com.contact_manager.app.repository.RolesRepository;
import com.contact_manager.app.repository.UserRepository;
import com.contact_manager.app.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private UserSignupMapper userSignupMapper;
    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder,UserSignupMapper userSignupMapper) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSignupMapper=userSignupMapper;
    }

    @Override
    public CustomResponseEntity<LoginRequest> login(LoginRequest loginRequest) {
        try {
            if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                logger.error("Username and password are required!");
                return CustomResponseEntity.error("Username and password are required!");
            }

            User user = userRepository.findByUsername(loginRequest.getUsername());
            if (user == null) {
                logger.error("Invalid username or password!");
                return CustomResponseEntity.error("Invalid username or password!");
            }

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                logger.error("Invalid username or password!");
                return CustomResponseEntity.error("Invalid username or password!");
            }

            return new CustomResponseEntity<>(loginRequest, "Login successful!");
        } catch (Exception e) {
            logger.error("An unexpected error occurred during login: {}", e.getMessage(), e);
            return CustomResponseEntity.error("An unexpected error occurred. Please try again later.");
        }
    }


    @Override
    public CustomResponseEntity<UserSignupDto> signup(UserSignupDto userSignupDto) {
        // Validate input
        if (userSignupDto.getUsername() == null || userSignupDto.getPassword() == null || userSignupDto.getEmail() == null) {
            return CustomResponseEntity.error("Username, password, and email are required!");
        }

        // Check if username or email already exists
        if (userRepository.existsByUsername(userSignupDto.getUsername())) {
            return CustomResponseEntity.error("Username is already registered!");
        }

        if (userRepository.existsByEmail(userSignupDto.getEmail())) {
            return CustomResponseEntity.error("Email is already in use!");
        }

        userSignupDto.setUsername(userSignupDto.getUsername());
        userSignupDto.setEmail(userSignupDto.getEmail());
        userSignupDto.setPhone(userSignupDto.getPhone());
        userSignupDto.setPassword(passwordEncoder.encode(userSignupDto.getPassword()));

        // Create new user
        User user = userSignupMapper.dtoToEntity(userSignupDto);
        user.setEnabled(true);

         Roles defaultRole = rolesRepository.findByRole(RoleTypes.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role not found!"));
        Set<Roles> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        userRepository.save(user);

        return new CustomResponseEntity<>(userSignupDto, "Signup successful!");
    }
}
