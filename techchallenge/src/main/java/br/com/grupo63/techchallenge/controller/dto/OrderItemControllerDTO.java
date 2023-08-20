package br.com.grupo63.techchallenge.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemControllerDTO extends AbstractControllerDTO {

    private Long quantity;
    private Double price;
    private Long productId;

}
