package com.gestion.tailleur.mapper;

import com.gestion.tailleur.Models.User;
import com.gestion.tailleur.dto.response.UserDTOresponse;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
public class UserDTOmapper implements Function<User, UserDTOresponse> {
    @Override
    public UserDTOresponse apply(User user) {
        return new UserDTOresponse(
                user.getId(),
                user.getNom(),
                user.getEmail(),
                user.isActif(),
                user.getRole()
        );
    }
}
