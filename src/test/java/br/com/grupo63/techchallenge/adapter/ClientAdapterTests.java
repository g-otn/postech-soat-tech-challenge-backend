package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.entity.client.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientAdapterTests {
    @Test
    void fillEntity_UsingDTO_ShouldFillEntity() {
        Client client = new Client();
        ClientControllerDTO dto = new ClientControllerDTO("5678");
        dto.setId(1234L);

        ClientAdapter.fillEntity(dto, client);

        Assertions.assertThat(client)
                .extracting(Client::getId, Client::getNationalId)
                .containsExactly(1234L, "5678");
    }

}
