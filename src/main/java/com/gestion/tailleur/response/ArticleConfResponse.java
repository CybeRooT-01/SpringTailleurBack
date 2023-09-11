package com.gestion.tailleur.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleConfResponse {
    private String message;
    private int status;

    public ArticleConfResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
