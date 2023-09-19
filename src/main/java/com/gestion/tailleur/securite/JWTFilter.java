package com.gestion.tailleur.securite;

import com.gestion.tailleur.Models.User;
import com.gestion.tailleur.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
   private UserService userService;
   private JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String userName = null;
        Boolean isTokenExpired  = true;
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            isTokenExpired = jwtService.isTokenExpired(token);
            userName = this.jwtService.ExtractUser(token);
        }

        if (!isTokenExpired && userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            boolean isTokenValid = this.jwtService.validateToken(token);
            if (!isTokenValid){
                throw new RuntimeException("Votre token est invalide");
            }
            UserDetails userDetails = this.userService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
