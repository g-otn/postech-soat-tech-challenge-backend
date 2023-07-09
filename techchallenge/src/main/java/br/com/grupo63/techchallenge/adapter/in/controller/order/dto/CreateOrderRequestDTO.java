package br.com.grupo63.techchallenge.adapter.in.controller.order.dto;

import br.com.grupo63.techchallenge.core.application.usecase.dto.ClientDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderItemDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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

        @Min(message = "orderitem.quantity.min", value = 1)
        private Long quantity;
    }

    @NotEmpty(message = "order.items.notempty")
    @Valid
    private List<Item> items;

    public OrderDTO toDomainDto(Long clientId) {
        OrderDTO orderDTO = new OrderDTO();

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientId);
        orderDTO.setClient(clientDTO);

        if (items != null) {
            orderDTO.setItems(items.stream()
                    .map(i -> new OrderItemDTO(i.quantity, null, i.id))
                    .collect(Collectors.toList()));
        }
        return orderDTO;
    }

}
