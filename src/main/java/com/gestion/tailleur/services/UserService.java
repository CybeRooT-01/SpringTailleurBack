package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.Role;
import com.gestion.tailleur.Models.User;
import com.gestion.tailleur.Models.Validation;
import com.gestion.tailleur.enums.TypeRoles;
import com.gestion.tailleur.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.Utilities;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    public void inscription(User user){
        if (user.getEmail().indexOf('@') == -1){
            throw  new RuntimeException("Votre email est incorrecte");
        }
        if (user.getEmail().indexOf('.') == -1){
            throw  new RuntimeException("Votre email est incorrecte");
        }
        Optional<User> userExist = this.userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()){
            throw new RuntimeException("Votre Email existe Deja");
        }
        String mdpChiffrer = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(mdpChiffrer);
        Role role = new Role();
        role.setLibelle(TypeRoles.UTILISATEUR);
        user.setRole(role);
        user =  this.userRepository.save(user);
        this.validationService.enregistrer(user);
    }

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.lireByCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre Code a exprier");
        }
         User userActif = this.userRepository.findById(validation.getUser().getId()).orElseThrow(()->new RuntimeException("Utiliseur introuvable"));
        userActif.setActif(true);
        this.userRepository.save(userActif);
    }
}
