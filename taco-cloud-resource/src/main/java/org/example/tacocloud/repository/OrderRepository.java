package org.example.tacocloud.repository;

import org.example.tacocloud.domain.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
