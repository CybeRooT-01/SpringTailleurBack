package com.gestion.tailleur.controllers;

import com.gestion.tailleur.Models.User;
import com.gestion.tailleur.dto.requests.AuthentificationDTO;
import com.gestion.tailleur.securite.JWTService;
import com.gestion.tailleur.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void inscription(@RequestBody User user){

        this.userService.inscription(user);
//        log.info("inscription");
    }
    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation){
        this.userService.activation(activation);
    }
    @PostMapping(path = "connexion")
    public Map<String, String>connexion(@RequestBody AuthentificationDTO authentificationDTO){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password()));
        if (authenticate.isAuthenticated()){
           return  this.jwtService.generate(authentificationDTO.username());
        }
        log.info("resultat {}", authenticate.isAuthenticated());
        return null;
    }

}
