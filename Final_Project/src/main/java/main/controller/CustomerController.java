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

    // get all customer in json format
    @RequestMapping("/customers")
    public List<Customer> getAllcustomers() {
        return (List<Customer>) customerService.getAll();
    }

    // get customer by inputted specific id
    @RequestMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return (Customer) customerService.get(id);
    }

    // function to add customer
    @RequestMapping(method = RequestMethod.POST, value = "/customers")
    public void addCustomer(@RequestBody Customer c) {
        try {
            customerService.add(c);
            System.out.println("Data added successfully");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    // function to delete customer by given id
    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
    }

    // function to update customer by given customer id
    @RequestMapping(method = RequestMethod.PUT, value = "customers/{id}")
    public void updateCustomer(@RequestBody Customer s, @PathVariable int id) {
        customerService.update(id, s);
    }
}
