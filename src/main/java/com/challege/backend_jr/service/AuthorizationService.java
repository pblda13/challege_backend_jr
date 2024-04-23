package com.challege.backend_jr.service;

import com.challege.backend_jr.dto.LoginResponseDto;
import com.challege.backend_jr.dto.RegisterDto;
import com.challege.backend_jr.dto.AuthenticationDto;
import com.challege.backend_jr.entity.User;
import com.challege.backend_jr.repositories.UserRepository;
import com.challege.backend_jr.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder

    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return user;
    }

    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data) {
        authenticationManager = context.getBean(AuthenticationManager.class);  // Get AuthenticationManager

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), passwordEncoder.encode(data.password()));  // Encode password before authentication
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (AuthenticationException e) {
            // Handle authentication failure
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        if (this.userRepository.findByUsername(registerDto.username()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = passwordEncoder.encode(registerDto.password());

        User newUser = new User(registerDto.username(), encryptedPassword, registerDto.role());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}

