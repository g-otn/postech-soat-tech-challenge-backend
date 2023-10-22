package br.com.grupo63.techchallenge.api.controller.order.dto;

import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderItemControllerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDTO {

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Item {
        private Long id;
        private Long quantity;
    }

    private List<Item> items;

}
