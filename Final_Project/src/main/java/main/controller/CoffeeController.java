package main.controller;

import main.service.CoffeeService;
import model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @RequestMapping("/coffees")
    public List<Coffee> getAllcoffees() {
        return (List<Coffee>) coffeeService.getAll();
    }

    @RequestMapping("/coffees/{id}")
    public Coffee getcoffee(@PathVariable int id) {
        return (Coffee) coffeeService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/coffees")
    public void addcoffee(@RequestBody Coffee p) {
        try {
            coffeeService.add(p);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/coffees/{id}")
    public void deletecoffee(@PathVariable int id) {
        coffeeService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "coffees/{id}")
    public void updatecoffee(@RequestBody Coffee p, @PathVariable int id) {
        coffeeService.update(id, p);
    }
}
