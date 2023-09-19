package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    JavaMailSender javaMailSender;
    public void envoyer (Validation validation){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("no-reply@Cyberoot.sn");
        mailMessage.setTo(validation.getUser().getEmail());
        mailMessage.setSubject("Votre code d'activation");
        String text =  String.format("Bonjour %s, <br/> votre code d'activation est %s", validation.getUser().getNom(), validation.getCode());
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

}
