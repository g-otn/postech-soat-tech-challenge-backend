package br.com.grupo63.techchallenge.core.application.usecase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends GenericException {


    public ValidationException(String name, String description) {
        super(name, description);
    }
}
