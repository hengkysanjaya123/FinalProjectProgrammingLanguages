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

    private List<Customer> customerList = new ArrayList<>(Arrays.asList(
            new Customer(1, "name 1", new Date(2000, 8, 19), "Male", 1, "customer", "customer", 1000)
    ));

    @Override
    public void add(IModel m) {
        customerList.add((Customer) m);
        super.add(m);
    }

    @Override
    public void delete(int id) {
        customerList.removeIf(t -> t.getCustomerId() == id);
        super.delete(id);
    }

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

    @Override
    public IModel get(int id) {
        return customerList.stream().filter(t -> t.getCustomerId() == id).findFirst().get();
    }

    @Override
    public List<? extends IModel> getAll() {
        return customerList;
    }
}
