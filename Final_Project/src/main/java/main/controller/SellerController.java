package main.controller;

import main.service.SellerService;
import model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @RequestMapping("/sellers")
    public List<Seller> getAllSellers() {
        return (List<Seller>) sellerService.getAll();
    }

    @RequestMapping("/sellers/{id}")
    public Seller getSeller(@PathVariable int id) {
        return (Seller) sellerService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sellers")
    public void addSeller(@RequestBody Seller s) {
        try {
            sellerService.add(s);
            System.out.println("Data added successfully");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sellers/{id}")
    public void deleteSeller(@PathVariable int id) {
        sellerService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "sellers/{id}")
    public void updateSeller(@RequestBody Seller s, @PathVariable int id) {
        sellerService.update(id, s);
    }
}
