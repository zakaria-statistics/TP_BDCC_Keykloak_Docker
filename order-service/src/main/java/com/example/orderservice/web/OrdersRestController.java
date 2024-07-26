package com.example.orderservice.web;

import com.example.orderservice.entities.Order;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.restClient.InventoryRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersRestController {
    private OrderRepository orderRepository;
    private InventoryRestClient inventoryRestClient;

    public OrdersRestController(OrderRepository orderRepository, InventoryRestClient inventoryRestClient) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
    }
    @GetMapping("/orders")
    public List<Order> findAllOrders(){
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(o->{
            o.getProductItems().forEach(pi->{
                pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
            });
        });
        return allOrders;
    }
    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable String id){
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(pi->{
            pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
        });
        return order;
    }


}
