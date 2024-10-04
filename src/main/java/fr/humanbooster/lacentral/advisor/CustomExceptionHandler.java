package fr.humanbooster.lacentral.advisor;

import fr.humanbooster.lacentral.custom_response.CustomResponse;
import fr.humanbooster.lacentral.exception.CustomEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CustomEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    CustomResponse entityNotFoundHandler(CustomEntityNotFoundException exception) {
        CustomResponse response = new CustomResponse();
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        response.setField(exception.getField());
        response.setValue(exception.getValue());
        response.setEntity(exception.getEntity());
        return response;
    }

}