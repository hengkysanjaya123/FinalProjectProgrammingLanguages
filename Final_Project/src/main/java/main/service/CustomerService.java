package main.service;

import model.Customer;
import model.IModel;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService extends CRUDOperation {

    // initialize customer data
    private List<Customer> customerList = new ArrayList<>(Arrays.asList(
            new Customer(1, "name 1", new Date(2000, 8, 19), "Male", 1, "customer", "customer", 1000)
    ));

    // function to add customer
    @Override
    public void add(IModel m) {
        customerList.add((Customer) m);
        super.add(m);
    }

    // function to delete customer by given customer id
    @Override
    public void delete(int id) {
        customerList.removeIf(t -> t.getCustomerId() == id);
        super.delete(id);
    }

    // function update customer
    @Override
    public void update(int id, IModel m) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getCustomerId() == id) {
                customerList.set(i, (Customer) m);
                super.update(id, m);
                break;
            }
        }
    }

    // function to get customer by given customer id
    @Override
    public IModel get(int id) {
        return customerList.stream().filter(t -> t.getCustomerId() == id).findFirst().get();
    }

    // function to get all customer data
    @Override
    public List<? extends IModel> getAll() {
        return customerList;
    }
}
