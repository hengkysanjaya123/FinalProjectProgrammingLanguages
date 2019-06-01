package main.service;

import model.IModel;
import model.Order;
import model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderService extends CRUDOperation {
    private List<Order> orderList = new ArrayList<>(Arrays.asList(
            new Order(1, LocalDate.of(2019, 5, 31), 1, new ArrayList<>(Arrays.asList(
                    new OrderDetail(1, 1, 1, 10),
                    new OrderDetail(2, 1, 2, 10)
            )))
    ));

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public void add(IModel m) {
        int id = 1;
        Order maxOrder = orderList.stream().max(Comparator.comparing(Order::getId)).get();
        if (maxOrder != null) {
            id = maxOrder.getId() + 1;
        }

        Order order = (Order) m;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            OrderDetail currentDetail = order.getOrderDetails().get(i);
            orderDetailList.add(new OrderDetail(i + 1, id, currentDetail.getProductId(), currentDetail.getQty()));
        }
        orderList.add(new Order(id, LocalDate.now(), order.getCustomerId(), orderDetailList));

        super.add(m);
    }

    @Override
    public void delete(int id) {
        orderList.removeIf(t -> t.getId() == id);
        orderDetailService.deleteOrder(id);
        super.delete(id);
    }

    @Override
    public void update(int id, IModel m) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == id) {
                orderList.set(i, (Order) m);
                super.update(id, m);
                break;
            }
        }
    }

    @Override
    public IModel get(int id) {
        return orderList.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    @Override
    public List<? extends IModel> getAll() {
        return orderList;
    }
}
