package br.com.grupo63.techchallenge.adapter.in.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DefaultResponseDTO {
    private String title = "Internal server error";
    private String description = "An unknown server error happened try again.";
}
