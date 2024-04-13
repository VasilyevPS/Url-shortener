package vasilyevps.urlshortener;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ResponseBody
@ControllerAdvice
public class BaseExceptionHandler {

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validationExceptionsHandler(MethodArgumentNotValidException exception) {
        return "URL is missing";
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementExceptionHandler(NoSuchElementException exception) {
        return "URL not found";
    }
}
