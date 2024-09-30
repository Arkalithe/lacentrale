package fr.humanbooster.lacentral.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class ExpiredCodeException extends RuntimeException {
    private String field;
    private Object value;
    private String entity;


    public ExpiredCodeException(String s) {
        super(s);
    }
}