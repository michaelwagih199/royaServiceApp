package com.omelnur.roya.royaServiceApp.auth.controllers;


import com.omelnur.roya.royaServiceApp.auth.models.Role;
import com.omelnur.roya.royaServiceApp.auth.models.User;
import com.omelnur.roya.royaServiceApp.auth.payload.request.LoginRequest;
import com.omelnur.roya.royaServiceApp.auth.payload.request.SignupRequest;
import com.omelnur.roya.royaServiceApp.auth.payload.response.JwtResponse;
import com.omelnur.roya.royaServiceApp.auth.payload.response.MessageResponse;
import com.omelnur.roya.royaServiceApp.auth.repository.RoleRepository;
import com.omelnur.roya.royaServiceApp.auth.repository.UserRepository;
import com.omelnur.roya.royaServiceApp.auth.security.jwt.JwtUtils;
import com.omelnur.roya.royaServiceApp.auth.security.services.UserDetailsImpl;
import com.omelnur.roya.royaServiceApp.auth.service.userLogs.StaticLog;
import com.omelnur.roya.royaServiceApp.auth.service.userLogs.UserLogsService;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserLogsService userLogsService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        userLogsService.createLoginLogs(StaticLog.createLogin(loginRequest.getUsername()), (long)1 , loginRequest.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        var permission = userRepository.findByUsername(loginRequest.getUsername())
                .map(user -> user.getRoles()
                        .stream()
                        .map(role ->
                                role.getPermissions()).
                                map(permissions ->permissions.stream()
                                        .map(permission1 -> permission1.getPermissionName())
                                        .collect(Collectors.toList())));

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                permission));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest,
                                          @Positive @RequestParam Long roleId) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("role id " + roleId + " notFound"));
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /**
     * todo
     * getUsers()
     * findUserById(id)
     * deleteUser()
     */

    @GetMapping("users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> findUserById(@Positive @PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }


    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = new User();
        userRepository.findById(id).map(u -> {
            user.setId(u.getId());
            user.setEmail(u.getEmail());
            user.setPassword(u.getPassword());
            user.setUsername(u.getUsername());
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userRepository.delete(user);

    }

}
