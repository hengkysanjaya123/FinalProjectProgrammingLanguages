package main.controller;

import main.service.CustomerService;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public List<Customer> getAllcustomers() {
        return (List<Customer>) customerService.getAll();
    }

    @RequestMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return (Customer) customerService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers")
    public void addCustomer(@RequestBody Customer c) {
        try {
            customerService.add(c);
            System.out.println("Data added successfully");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "customers/{id}")
    public void updateCustomer(@RequestBody Customer s, @PathVariable int id) {
        customerService.update(id, s);
    }
}
