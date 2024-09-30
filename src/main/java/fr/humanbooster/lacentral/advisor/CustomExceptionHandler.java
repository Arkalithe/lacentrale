package fr.humanbooster.lacentral.advisor;

import fr.humanbooster.lacentral.custom_response.CustomResponse;
import fr.humanbooster.lacentral.exception.AlreadyActiveException;
import fr.humanbooster.lacentral.exception.ExpiredCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public CustomResponse handler(RuntimeException ex) {
      if (ex instanceof  AlreadyActiveException || ex instanceof ExpiredCodeException) {
          return new CustomResponse(HttpStatus.BAD_GATEWAY.value(), ex.getMessage());
      }
      return new CustomResponse();
    }

}