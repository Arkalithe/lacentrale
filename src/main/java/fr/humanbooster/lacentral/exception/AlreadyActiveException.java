package fr.humanbooster.lacentral.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class AlreadyActiveException extends RuntimeException {

    public AlreadyActiveException(String s) {
        super(s);
    }
}

