package br.com.grupo63.techchallenge.core.domain.model.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    RECEIVED, PREPARING, READY, FINISHED;

}
