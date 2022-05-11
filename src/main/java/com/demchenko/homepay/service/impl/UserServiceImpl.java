package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.UserLoginForm;
import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.request.UserUpdateForm;
import com.demchenko.homepay.dto.response.JwtResponse;
import com.demchenko.homepay.dto.response.MessageResponse;
import com.demchenko.homepay.entity.Role;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.repository.UserRepository;
import com.demchenko.homepay.security.jwt.JwtUtils;
import com.demchenko.homepay.security.service.UserDetailsImpl;
import com.demchenko.homepay.service.UserService;
import com.demchenko.homepay.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user with this id not found"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).
                orElseThrow(()-> new RuntimeException("USER NOT FOUND"));
    }

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBalance(BigDecimal.valueOf(0));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> registryUser(UserRegistryForm userRegistryForm) {
        if(existByEmail(userRegistryForm.email())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("User with this email is already in use"));
        }
        createUser(userRegistryForm.firstName(), userRegistryForm.lastName(),
                userRegistryForm.email(), passwordEncoder.encode(userRegistryForm.password()));
        return ResponseEntity.ok(new MessageResponse("User is successfully registered"));
    }

    @Override
    public void updateUser(UserUpdateForm userUpdateForm) {
        User user = findUserById(userUpdateForm.userId());
        user.setFirstName(userUpdateForm.firstName());
        user.setLastName(userUpdateForm.lastName());
        user.setEmail(userUpdateForm.email());
        userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsersBy(String lastName, String email) {
        UserSpecification userSpecification = new UserSpecification(lastName, email);
        return userRepository.findAll(userSpecification);
    }



    @Override
    public ResponseEntity<JwtResponse> authenticateUser(UserLoginForm userLoginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginForm.email(), userLoginForm.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().findFirst().toString();
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwt);
        jwtResponse.setId(userDetails.getId());
        jwtResponse.setEmail(userDetails.getUsername());
        if(userDetails.getAuthorities().contains(Role.ROLE_USER)) {
            jwtResponse.setRole(Role.ROLE_USER);
        }
        else {
            jwtResponse.setRole(Role.ROLE_ADMIN);
        }
        return ResponseEntity.ok(jwtResponse);
    }
}
