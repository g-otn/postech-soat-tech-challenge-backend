package br.com.grupo63.techchallenge.entity.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    RECEIVED, PREPARING, READY, FINISHED;

}
