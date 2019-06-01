package main.service;

import model.IModel;
import model.Store;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreService extends CRUDOperation {
    public List<Store> storeList = new ArrayList<Store>(Arrays.asList(
            new Store(1, "Store 1", 1),
            new Store(2, "Store 2", 1),
            new Store(3, "Store 3", 1)
    ));

    @Override
    public void add(IModel m) {
        storeList.add((Store) m);
        super.add(m);
    }

    @Override
    public void delete(int id) {
        storeList.removeIf(t -> t.getId() == id);
        super.delete(id);
    }

    @Override
    public void update(int id, IModel m) {
        for (int i = 0; i < storeList.size(); i++) {
            if (storeList.get(i).getId() == id) {
                storeList.set(i, (Store) m);
                super.update(id, m);
                break;
            }
        }
    }

    @Override
    public IModel get(int id) {
        return storeList.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    @Override
    public List<? extends IModel> getAll() {
        return storeList;
    }
}
