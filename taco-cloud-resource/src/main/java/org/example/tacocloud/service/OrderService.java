package org.example.tacocloud.service;

import lombok.RequiredArgsConstructor;
import org.example.tacocloud.repository.OrderRepositoryJpa;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepositoryJpa orderRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrders() {
        orderRepository.deleteAll();
    }
}
