package main.controller;

import main.service.OrderService;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return (List<Order>) orderService.getAll();
    }

    @RequestMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id) {
        return (Order) orderService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/orders")
    public void addOrder(@RequestBody Order p) {
        try {
            orderService.add(p);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/orders/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "orders/{id}")
    public void updateOrder(@RequestBody Order p, @PathVariable int id) {
        orderService.update(id, p);
    }
}
