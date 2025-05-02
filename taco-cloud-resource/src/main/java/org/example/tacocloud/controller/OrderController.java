package org.example.tacocloud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tacocloud.domain.TacoOrder;
import org.example.tacocloud.domain.User;
import org.example.tacocloud.property.TacoProperties;
import org.example.tacocloud.repository.OrderRepositoryJpa;
import org.example.tacocloud.repository.TacoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepositoryJpa orderRepository;
    private final TacoProperties tacoProperties;
    private final TacoRepository tacoRepository;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        tacoOrder.setUser(user);

        log.info("Order submitted: {}", tacoOrder);
        orderRepository.save(tacoOrder);
        sessionStatus.setComplete();

        return "finish";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {

        Pageable pageable = PageRequest.of(0, tacoProperties.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orders";
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder order) {

        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder tacoOrder) {
        TacoOrder order = orderRepository.findById(orderId).orElse(new TacoOrder());
        if (tacoOrder.getPlacedAt() != null) {
            order.setPlacedAt(tacoOrder.getPlacedAt());
        }
        if (tacoOrder.getDeliveryCity() != null) {
            order.setDeliveryCity(tacoOrder.getDeliveryCity());
        }
        if (tacoOrder.getDeliveryState() != null) {
            order.setDeliveryState(tacoOrder.getDeliveryState());
        }
        if (tacoOrder.getDeliveryZip() != null) {
            order.setDeliveryZip(tacoOrder.getDeliveryZip());
        }
        if (tacoOrder.getDeliveryStreet() != null) {
            order.setDeliveryStreet(tacoOrder.getDeliveryStreet());
        }
        if (tacoOrder.getCcNumber() != null) {
            order.setCcNumber(tacoOrder.getCcNumber());
        }
        if (tacoOrder.getCcExpiration() != null) {
            order.setCcExpiration(tacoOrder.getCcExpiration());
        }
        if (tacoOrder.getCcCVV() != null) {
            order.setCcCVV(tacoOrder.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            tacoRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            log.debug(e.getMessage());
        }
    }


}
