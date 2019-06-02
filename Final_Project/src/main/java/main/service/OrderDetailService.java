package main.service;

import model.IModel;
import model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderDetailService extends CRUDOperation {

    // initialize order detail data
    private List<OrderDetail> detailOrderList = new ArrayList<>(Arrays.asList(
            new OrderDetail(1, 1, 1, 10)
    ));

    // function to add order detail
    @Override
    public void add(IModel m) {
        detailOrderList.add((OrderDetail) m);
        super.add(m);
    }

    // function to delete order by given order id
    public void deleteOrder(int orderId) {
        detailOrderList.removeIf(t -> t.getOrderId() == orderId);
    }

    // function to delete order by given orderdetail id
    @Override
    public void delete(int id) {
        detailOrderList.removeIf(t -> t.getId() == id);
        super.delete(id);
    }

    // function to update order detail
    @Override
    public void update(int id, IModel m) {
        for (int i = 0; i < detailOrderList.size(); i++) {
            if (detailOrderList.get(i).getId() == id) {
                detailOrderList.set(i, (OrderDetail) m);
                super.update(id, m);
                break;
            }
        }
    }

    // function to get OrderDetail data by given orderdetail id
    @Override
    public IModel get(int id) {
        return detailOrderList.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    // function to get all order detail data
    @Override
    public List<? extends IModel> getAll() {
        return detailOrderList;
    }
}
