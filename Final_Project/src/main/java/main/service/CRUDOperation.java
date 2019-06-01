package main.service;

import model.IModel;

import java.util.List;

public abstract class CRUDOperation {
    public void add(IModel m) {
        System.out.println("Data added successfully");
    }

    public void delete(int id) {
        System.out.println("Data deleted successfully");
    }

    public void update(int id, IModel m) {
        System.out.println("Data updated successfully");
    }

    public abstract IModel get(int id);

    public abstract List<? extends IModel> getAll();
}
