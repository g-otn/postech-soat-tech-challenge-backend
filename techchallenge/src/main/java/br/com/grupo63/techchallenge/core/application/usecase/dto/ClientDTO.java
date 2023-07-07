package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {

    @Schema(defaultValue = "123")
    private Long id;

    @Schema(example = "01234567890")
    private String nationalId;

    public static ClientDTO toDto(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setNationalId(client.getNationalId());

        return clientDTO;
    }

    public void toDomain(Client client) {
        client.setId(id);
        client.setNationalId(nationalId);
    }
}
