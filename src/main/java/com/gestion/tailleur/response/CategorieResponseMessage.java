package com.gestion.tailleur.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorieResponseMessage<T> {
    private String message;
    private T data;
    private int status;

    public CategorieResponseMessage(String message, T data, int status) {
        this.message = message;
        this.data = data;
        this.status=status;
    }

}