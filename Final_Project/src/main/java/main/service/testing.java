package main.service;

import model.IModel;

import java.util.List;

public class testing extends CRUDOperation {

    @Override
    public void add(IModel m) {
        super.add(m);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(int id, IModel m) {
        super.update(id, m);
    }

    @Override
    public IModel get(int id) {
        return null;
    }

    @Override
    public List<? extends IModel> getAll() {
        return null;
    }
}
