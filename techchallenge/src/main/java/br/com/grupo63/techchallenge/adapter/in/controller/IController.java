package br.com.grupo63.techchallenge.adapter.in.controller;

import br.com.grupo63.techchallenge.adapter.in.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.GenericException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface IController {

    @ExceptionHandler
    default ResponseEntity<DefaultResponseDTO> handleException(Exception exception) {
        DefaultResponseDTO responseDTO = new DefaultResponseDTO();

        if (exception instanceof ValidationException validationException) {
            responseDTO.setTitle(validationException.getName());
            responseDTO.setDescription(validationException.getDescription());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        } else if (exception instanceof GenericException genericException) {
            responseDTO.setTitle(genericException.getName());
            responseDTO.setDescription(genericException.getDescription());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

        return ResponseEntity.internalServerError().body(responseDTO);
    }
}
