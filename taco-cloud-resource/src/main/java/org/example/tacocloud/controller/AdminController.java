package org.example.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.example.tacocloud.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrderService orderService;

    @PostMapping("/deleteOrders")
    public String deleteOrders() {
        orderService.deleteOrders();
        return "redirect:/admin";
    }
}
