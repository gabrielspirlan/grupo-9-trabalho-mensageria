package com.grupo9.trabalho_mensageria.view;

import com.grupo9.trabalho_mensageria.application.OrderService;
import com.grupo9.trabalho_mensageria.domain.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Optional<Order>> getById(@PathVariable UUID uuid) {

        Optional<Order> order = orderService.findByUuid(uuid);

        if (order.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                order,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<Page<Order>> getAll(
            @RequestParam(required = false) Integer clientId,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {

        if (pageNumber == null) {
            pageNumber = 0;
        }


        if (pageSize == null) {
            pageSize = 10;
        } else if (pageSize > 50) {
            pageSize = 50;
        }


        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new ResponseEntity<>(
                orderService.findAll(clientId,productId, status, pageable),
                HttpStatus.OK
        );
    }
}
