package br.com.grupo63.techchallenge.adapter.driver.controllers;

import br.com.grupo63.techchallenge.core.domain.entities.DummyEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class DummyController {

    @GetMapping
    public String teste() {
        return "teste";
    }

    @GetMapping("/entidade")
    public DummyEntity testeEntidade() {
        return new DummyEntity(-1L, "aaa");
    }

}
