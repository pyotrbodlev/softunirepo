package alararestaurant.service;

import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private FileUtil fileUtil;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, FileUtil fileUtil) {
        this.orderRepository = orderRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/orders.xml").getFile());
    }

    @Override
    public String importOrders() {
        // TODO : Implement me
        return null;
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        // TODO : Implement me
        return null;
    }
}
