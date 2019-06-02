package main.controller;

import main.service.CoffeeService;
import model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    It’s a convenience annotation that combines @Controller and @ResponseBody –
    which eliminates the need to annotate every request handling method of the controller class
    with the @ResponseBody annotation.
 */
@RestController
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    // function to return all coffees data in json format
    @RequestMapping("/coffees")
    public List<Coffee> getAllcoffees() {
        return (List<Coffee>) coffeeService.getAll();
    }

    // function to return coffee by specific coffee id inputted from parameter
    @RequestMapping("/coffees/{id}")
    public Coffee getcoffee(@PathVariable int id) {
        return (Coffee) coffeeService.get(id);
    }

    // function to add coffee
    @RequestMapping(method = RequestMethod.POST, value = "/coffees")
    public void addcoffee(@RequestBody Coffee p) {
        try {
            coffeeService.add(p);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    // function to delete coffee by specific coffee id
    // set the Request Method = RequestMethod.DELETE, and the path
    @RequestMapping(method = RequestMethod.DELETE, value = "/coffees/{id}")
    public void deletecoffee(@PathVariable int id) {
        coffeeService.delete(id);
    }

    // function to update coffee
    @RequestMapping(method = RequestMethod.PUT, value = "coffees/{id}")
    public void updatecoffee(@RequestBody Coffee p, @PathVariable int id) {
        coffeeService.update(id, p);
    }
}
