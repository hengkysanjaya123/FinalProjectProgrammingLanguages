package main.controller;

import main.service.CustomerService;
import main.service.SellerService;
import model.*;
import model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private CustomerService customerService;


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public LoginResult doLogin(@RequestBody Login login) {
        List<Seller> sellerList = (List<Seller>) sellerService.getAll();
        List<Customer> customerList = (List<Customer>) customerService.getAll();

        for (int i = 0; i < sellerList.size(); i++) {
            Seller cur = sellerList.get(i);
            if (cur.getEmail().equals(login.email) && cur.getPassword().equals(login.password)) {
                return new LoginResult(cur.getEmail(), cur.getPassword(), "seller");
            }
        }

        for (int i = 0; i < customerList.size(); i++) {
            Customer cur = customerList.get(i);
            if (cur.getEmail().equals(login.email) && cur.getPassword().equals(login.password)) {
                return new LoginResult(cur.getEmail(), cur.getPassword(), "customer");
            }
        }

        return null;
    }
}
