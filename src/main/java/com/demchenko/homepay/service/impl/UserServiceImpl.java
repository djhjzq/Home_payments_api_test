package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.UserLoginForm;
import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.request.UserUpdateForm;
import com.demchenko.homepay.dto.response.JwtResponse;
import com.demchenko.homepay.dto.response.UserResponse;
import com.demchenko.homepay.entity.Role;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.exception.UserNotFoundException;
import com.demchenko.homepay.mapper.UserMapper;
import com.demchenko.homepay.repository.UserRepository;
import com.demchenko.homepay.security.jwt.JwtUtils;
import com.demchenko.homepay.security.service.UserDetailsImpl;
import com.demchenko.homepay.service.UserService;
import com.demchenko.homepay.specification.UserSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserMapper userMapper, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User findUserById(Long userId) {
        log.info("Try to find user with id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException
                        ("User by id " + userId + " was not found."));
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("Try to find user with email: {}", email);
        return userRepository.findUserByEmail(email).
                orElseThrow(()-> new UserNotFoundException
                        ("User by email " + email + " was not found."));
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBalance(BigDecimal.valueOf(0));
        user.setRole(Role.ROLE_USER);
        log.info("Save user to repository with firstName: {}, lastName: {}," +
                "email: {}", firstName, lastName, email);
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<UserResponse> registryUser(UserRegistryForm userRegistryForm) {
        if(existByEmail(userRegistryForm.email())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = createUser(userRegistryForm.firstName(), userRegistryForm.lastName(),
                userRegistryForm.email(), passwordEncoder.encode(userRegistryForm.password()));
        return new ResponseEntity<>(userMapper.userToUserDto(user), HttpStatus.CREATED);
    }

    @Override
    public User updateUser(UserUpdateForm userUpdateForm) {
        User user = findUserById(userUpdateForm.userId());
        user.setFirstName(userUpdateForm.firstName());
        user.setLastName(userUpdateForm.lastName());
        user.setEmail(userUpdateForm.email());
        log.info("Update user in repository with firstName: {}, lastName{}, " +
                "email: {}", user.getFirstName(), user.getLastName(), user.getEmail());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean existByEmail(String email) {
        log.info("Check user by email: {}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Try to delete user by id: {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        log.info("Find all users on page: {}", pageable.getPageNumber());
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> searchUsersBy(String lastName, String email) {
        UserSpecification userSpecification = new UserSpecification(lastName, email);
        log.info("Try to find users by lastName: {}, email: {}", lastName, email);
        return userRepository.findAll(userSpecification);
    }



    @Override
    public JwtResponse authenticateUser(UserLoginForm userLoginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginForm.email(), userLoginForm.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
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
        log.info("Authenticate user with token: {}, email : {} ", jwtResponse.getToken(),
                jwtResponse.getEmail());

        return jwtResponse;
    }

    @Override
    public void refreshCookie(HttpServletResponse response) {
        Cookie cookieAuth = new Cookie("Authorization", "");
        Cookie cookieRole = new Cookie("Role", "");
        Cookie cookieId = new Cookie("Id", "");
        cookieAuth.setPath("/");
        cookieRole.setPath("/");
        cookieId.setPath("/");
        cookieAuth.setMaxAge(0);
        cookieRole.setMaxAge(0);
        cookieId.setMaxAge(0);
        response.addCookie(cookieAuth);
        log.info("Adding Authorization cookie with value: {}", cookieAuth);
        response.addCookie(cookieRole);
        log.info("Adding Role cookie with value: {}", cookieRole);
        response.addCookie(cookieId);
        log.info("Adding Id cookie with value: {}", cookieId);
    }

    @Override
    public void addCookies(JwtResponse jwtResponse, HttpServletResponse response) {
        Cookie cookieAuth = new Cookie("Authorization", "Bearer"+jwtResponse.getToken());
        Cookie cookieRole = new Cookie("Role", jwtResponse.getRole().toString());
        Cookie cookieId = new Cookie("Id", jwtResponse.getId().toString());
        cookieAuth.setPath("/");
        cookieRole.setPath("/");
        cookieId.setPath("/");
        response.addCookie(cookieAuth);
        log.info("Adding Authorization cookie with value: {}", cookieAuth.getValue());
        response.addCookie(cookieRole);
        log.info("Adding Role cookie with value: {}", cookieRole.getValue());
        response.addCookie(cookieId);
        log.info("Adding Id cookie with value: {}", cookieId);
    }
}
