package com.gestion.tailleur.controllers;

import com.gestion.tailleur.Models.User;
import com.gestion.tailleur.dto.requests.AuthentificationDTO;
import com.gestion.tailleur.dto.requests.UserDTOrequest;
import com.gestion.tailleur.dto.response.AuthResponse;
import com.gestion.tailleur.securite.JWTService;
import com.gestion.tailleur.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody UserDTOrequest user){
        this.userService.inscription(user);
    }
    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation){
        this.userService.activation(activation);
    }
    @PostMapping(path = "connexion")
    public AuthResponse connexion(@RequestBody AuthentificationDTO authentificationDTO){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password()));
        if (authenticate.isAuthenticated()){
           return  this.jwtService.generate(authentificationDTO.username());
        }
        return null;
    }

    @PostMapping(path = "deconnexion")
    public ResponseEntity<?>deconnection(HttpServletRequest request){
        String tokenWithBearer = request.getHeader("Authorization");
        String token = tokenWithBearer.substring(7);
//        System.out.println(token);
        this.jwtService.invalidate(token);
        return ResponseEntity.ok("Vous etes deconnecter");
    }


}








