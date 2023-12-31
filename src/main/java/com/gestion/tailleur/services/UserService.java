package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.Role;
import com.gestion.tailleur.Models.User;
import com.gestion.tailleur.Models.Validation;
import com.gestion.tailleur.dto.requests.UserDTOrequest;
import com.gestion.tailleur.enums.TypeRoles;
import com.gestion.tailleur.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
//@NoArgsConstructor
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private  UserRepository userRepository;
    private  BCryptPasswordEncoder passwordEncoder;
    private  ValidationService validationService;
    public void inscription(UserDTOrequest userDTOrequest){
        User user = new User();
        user.setEmail(userDTOrequest.email());
        user.setNom(userDTOrequest.nom());
        if (userDTOrequest.email().indexOf('@') == -1){
            throw  new RuntimeException("Votre email est incorrecte");
        }
        if (userDTOrequest.email().indexOf('.') == -1){
            throw  new RuntimeException("Votre email est incorrecte");
        }
        String userEmail = userDTOrequest.email();
        Optional<User> userExist = this.userRepository.findByEmail(userEmail);
        System.out.println(userExist);
        System.out.println(userEmail);
        if (userExist.isPresent()){
            throw new RuntimeException("Votre Email existe Deja");
        }
        String mdpChiffrer = this.passwordEncoder.encode(userDTOrequest.password());
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

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Aucun utilisateur trouver"));
    }
}
