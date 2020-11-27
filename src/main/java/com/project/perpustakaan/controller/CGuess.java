package com.project.perpustakaan.controller;

import javax.validation.Valid;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.ui.Model;
import com.project.perpustakaan.exception.AppException;
import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.model.Role;
import com.project.perpustakaan.model.RoleName;
import com.project.perpustakaan.model.User;
import com.project.perpustakaan.payload.ApiResponse;
import com.project.perpustakaan.payload.JwtAuthenticationResponse;
import com.project.perpustakaan.payload.LoginRequest;
import com.project.perpustakaan.payload.SignUpRequest;
import com.project.perpustakaan.repository.KatalogRepo;
import com.project.perpustakaan.repository.RoleRepository;
import com.project.perpustakaan.repository.UserRepository;
import com.project.perpustakaan.security.JwtTokenProvider;
import com.project.perpustakaan.service.FilesStorageService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@CrossOrigin
@RequestMapping(path = "/guess")
public class CGuess {

    @Autowired
    private KatalogRepo katalogRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    FilesStorageService storageService;

    // line awal pengolahan katalog
    @GetMapping(path = "/k/{id}")
    public Katalog idkatalog(@PathVariable Long id) {
        return katalogRepo.findById(id).get();
    }

    @GetMapping(path = "/k")
    public List<Katalog> get_all() {
        return katalogRepo.findAll();

    }

    // line akhir pengolahan katalog

    // line awal pengolahan login

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (Exception e) {
            System.out.println("fungsi gagal dijalankan");
            e.printStackTrace();
            return null;
            // TODO: handle exception
        }
    }

    // @PostMapping("/signup")
    @RequestMapping(path = "/signup", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<?> registerUser(@Valid @RequestPart("user") SignUpRequest signUpRequest,
            @RequestPart("file") MultipartFile file) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getNoHp(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getFoto(), signUpRequest.getPassword());
        //menyimpan foto
        storageService.save(file);
        String url = "http://localhost:8081/files/".concat(file.getOriginalFilename());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFoto(url);
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)// tempat menaruh role admin atau user
                .orElseThrow(() -> new AppException("User Role not set."));
        // masih salah membuat role
        user.setRoles(Collections.singleton(userRole));
        // System.out.println("INI ROLES COYY = " + user.getRoles());
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    // line akhir pengolahan login

    // mencoba view dengan gretting

    // akhir menciba view

}
