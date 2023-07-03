package br.com.grupo63.techchallenge.core.application.usecase.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class GenericException extends Exception {
    private String name;
    private String description;
}
