package main.controller;

import main.service.StoreService;
import model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping("/store")
    public List<Store> getAllstore() {
        return (List<Store>) storeService.getAll();
    }

    @RequestMapping("/store/{id}")
    public Store getStore(@PathVariable int id) {
        return (Store) storeService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/store")
    public void addStore(@RequestBody Store s) {
        try {
            storeService.add(s);
            System.out.println("Data added successfully");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/store/{id}")
    public void deleteStore(@PathVariable int id) {
        storeService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "store/{id}")
    public void updateStore(@RequestBody Store s, @PathVariable int id) {
        storeService.update(id, s);
    }

}
