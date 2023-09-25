package com.gestion.tailleur.dto.response;

import com.gestion.tailleur.Models.User;

import java.util.Map;

public record AuthResponse(
    Map<String, String> token,
    User user
) {
}
