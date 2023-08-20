package br.com.grupo63.techchallenge.api.controller.order.dto;

import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderItemControllerDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    private Long clientId;

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Item {
        @NotNull(message = "order.create.item.productIdNotNull")
        @Min(value = 1, message = "order.create.item.productIdValid")
        private Long id;
        @NotNull(message = "order.create.item.quantityNotNull")
        @Min(value = 1, message = "order.create.item.moreThan1Quantity")
        private Long quantity;
    }

    @Valid
    @NotNull(message = "order.create.items.notEmpty")
    @Size(min = 1, message = "order.create.items.notEmpty")
    private List<Item> items;

    public OrderControllerDTO toDomainDto(Long clientId) {
        OrderControllerDTO orderDTO = new OrderControllerDTO();

        ClientControllerDTO clientDTO = new ClientControllerDTO();
        clientDTO.setId(clientId);
        orderDTO.setClient(clientDTO);

        if (items != null) {
            orderDTO.setItems(items.stream()
                    .map(i -> new OrderItemControllerDTO(i.quantity, null, i.id))
                    .collect(Collectors.toList()));
        }
        return orderDTO;
    }

}
