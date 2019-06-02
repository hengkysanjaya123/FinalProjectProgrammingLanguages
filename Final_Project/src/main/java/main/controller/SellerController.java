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

    // function to get all sellers data
    @RequestMapping("/sellers")
    public List<Seller> getAllSellers() {
        return (List<Seller>) sellerService.getAll();
    }

    // function to get get seller by specific given id
    @RequestMapping("/sellers/{id}")
    public Seller getSeller(@PathVariable int id) {
        return (Seller) sellerService.get(id);
    }

    // function to add seller data
    @RequestMapping(method = RequestMethod.POST, value = "/sellers")
    public void addSeller(@RequestBody Seller s) {
        try {
            sellerService.add(s);
            System.out.println("Data added successfully");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    // function to delete seller
    @RequestMapping(method = RequestMethod.DELETE, value = "/sellers/{id}")
    public void deleteSeller(@PathVariable int id) {
        sellerService.delete(id);
    }

    // function to update seller
    @RequestMapping(method = RequestMethod.PUT, value = "sellers/{id}")
    public void updateSeller(@RequestBody Seller s, @PathVariable int id) {
        sellerService.update(id, s);
    }
}
