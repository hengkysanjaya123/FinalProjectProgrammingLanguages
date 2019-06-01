package main.service;

import javafx.util.Pair;
import model.IModel;
import model.Seller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SellerService extends CRUDOperation {
    private List<Seller> sellerList = new ArrayList<>(Arrays.asList(
            new Seller(1, "name 1", new Date(2000, 8, 19), "male", 1, "seller", "seller")
    ));

    @Override
    public void add(IModel m) {
        sellerList.add((Seller) m);
        super.add(m);
    }

    @Override
    public void delete(int id) {
        sellerList.removeIf(t -> t.getSellerId() == id);
        super.delete(id);
    }

    @Override
    public void update(int id, IModel m) {
        for (int i = 0; i < sellerList.size(); i++) {
            if (sellerList.get(i).getSellerId() == id) {
                sellerList.set(i, (Seller) m);
                super.update(id, m);
                break;
            }
        }
    }

    @Override
    public IModel get(int id) {
        return sellerList.stream().filter(t -> t.getSellerId() == id).findFirst().get();
    }

    @Override
    public List<? extends IModel> getAll() {
        return sellerList;
    }
}
