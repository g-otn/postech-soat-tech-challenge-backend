package br.com.grupo63.techchallenge.adapter.driver.controller;

import br.com.grupo63.techchallenge.adapter.driven.infrastructure.ClientRepository;
import br.com.grupo63.techchallenge.core.domain.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/teste")
public class DummyController {

    private final ClientRepository clientRepository;

    @GetMapping
    public String teste() {
        return "teste";
    }

    @GetMapping("/entidade")
    public String testeEntidade() {
        this.clientRepository.save(new Client("12345678900"));
        return "aaa";
    }

}
