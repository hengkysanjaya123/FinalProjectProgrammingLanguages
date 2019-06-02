package main.controller;

import main.service.OrderDetailService;
import model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    // function to get all orderdetails data in json format
    @RequestMapping("/orderdetails")
    public List<OrderDetail> getAllOrderDetails() {
        return (List<OrderDetail>) orderDetailService.getAll();
    }

    // function to getorderdetail in specific given orderdetailid
    @RequestMapping("/orderdetails/{id}")
    public OrderDetail getOrderDetail(@PathVariable int id) {
        return (OrderDetail) orderDetailService.get(id);
    }

    // function to add orderdetail
    @RequestMapping(method = RequestMethod.POST, value = "/orderdetails")
    public void addOrderDetail(@RequestBody OrderDetail od) {
        try {
            orderDetailService.add(od);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    // function to delete order detail
    @RequestMapping(method = RequestMethod.DELETE, value = "/orderdetails/{id}")
    public void deleteOrderDetail(@PathVariable int id) {
        orderDetailService.delete(id);
    }

    // function to update order detail
    @RequestMapping(method = RequestMethod.PUT, value = "orderdetails/{id}")
    public void updateOrderDetail(@RequestBody OrderDetail od, @PathVariable int id) {
        orderDetailService.update(id, od);
    }
}
